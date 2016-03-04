package no.westerdals.westbook.rest;

import no.westerdals.westbook.model.Page;
import no.westerdals.westbook.model.Tag;
import no.westerdals.westbook.model.User;
import no.westerdals.westbook.mongodb.PageRepository;
import no.westerdals.westbook.mongodb.TagRepository;
import no.westerdals.westbook.mongodb.UserRepository;
import no.westerdals.westbook.responses.ResolvedTag;
import no.westerdals.westbook.responses.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("/rest/v1/search")
@RestController
public class SearchRestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private TagRepository tagRepository;

    @RequestMapping(method=RequestMethod.GET)
    public List<SearchResult> searchAll(@RequestParam String query, @RequestParam(name="maxResults",defaultValue="20") int maxResults) {
        ArrayList<SearchResult> results = new ArrayList<>(searchUsers(query, maxResults));
        results.addAll(searchPages(query, maxResults));
        results.addAll(searchTags(query, maxResults));
        return results;
    }

    @RequestMapping(value="/users",method=RequestMethod.GET)
    public List<SearchResult<User>> searchUsers(@RequestParam String query, @RequestParam(name="maxResults",defaultValue="20") int maxResults) {
        String[] nameParts = query.split(" ");
        if (nameParts.length > 4)
            return null;

        ArrayList<SearchResult<User>> found = new ArrayList<>();

        findUsersByFullName(found, query, maxResults);

        if (found.size() < maxResults) {
            findUsersBySurname(found, query, maxResults);
        }
        return found;
    }

    @RequestMapping(value="/pages",method=RequestMethod.GET)
    public List<SearchResult<Page>> searchPages(@RequestParam String query, @RequestParam(name="maxResults",defaultValue="20") int maxResults) {
        return pageRepository.findByName(query, new PageRequest(0, maxResults))
                .stream()
                .map(page -> new SearchResult<>("page", page))
                .collect(Collectors.toList());
    }

    @RequestMapping
    public List<SearchResult<ResolvedTag>> searchTags(@RequestParam String query, @RequestParam(name="maxResults",defaultValue="20") int maxResults) {
        return Stream.concat(tagRepository.findByName(query, new PageRequest(0, maxResults)).stream(),
                tagRepository.findByDescription(query, new PageRequest(0, maxResults)).stream())
                .map(this::resolve)
                .map(tag -> new SearchResult<>("tag", tag))
                .collect(Collectors.toList());
    }

    private void findUsersByFullName(List<SearchResult<User>> found, String fullname, int maxResults) {
        String[] nameParts = fullname.split(" ");
        if (nameParts.length == 1) {
            List<User> users = userRepository.findByFirstnameLikeIgnoreCase(nameParts[0], new PageRequest(0, maxResults));
            if (users != null) {
                users.stream().map(user -> new SearchResult<>("user", user))
                        .forEach(found::add);
            }
            return;
        }
        for (int i = 1; i < nameParts.length; i++) {
            String possibleName = join(nameParts, 0, i);
            String possibleSurname = join(nameParts, i, nameParts.length);
            List<User> users = userRepository.findByFirstnameLikeAndSurnameLikeIgnoreCase(possibleName, possibleSurname);
            if (users != null) {
                users.stream().map(user -> new SearchResult<>("user", user))
                        .forEach(found::add);
            }
            if (found.size() >= maxResults)
                return;
        }
    }

    private void findUsersBySurname(List<SearchResult<User>> found, String surname, int maxResults) {
        userRepository.findBySurnameLikeIgnoreCase(surname, new PageRequest(0, maxResults - found.size()))
                .stream()
                .filter(user -> !found.contains(user))
                .map(user -> new SearchResult<>("user", user))
                .forEach(found::add);
    }

    private ResolvedTag resolve(Tag tag) {
        ResolvedTag resolvedTag = new ResolvedTag(tag.getId(), null, tag.getName(), tag.getDescription());
        if (tag.getParentId() == null) {
            return resolvedTag;
        }
        Tag parent = tagRepository.findOne(tag.getParentId());
        resolvedTag.setParent(resolve(parent));
        return resolvedTag;
    }

    private String join(String[] strs, int startIndex, int endIndex) {
        StringBuilder result = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            result.append(strs[i]);
        }
        return result.toString();
    }
}
