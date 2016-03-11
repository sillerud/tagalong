package no.westerdals.westbook.rest;

import no.westerdals.westbook.MessageConstant;
import no.westerdals.westbook.model.Post;
import no.westerdals.westbook.model.UserCredentials;
import no.westerdals.westbook.mongodb.PostRepository;
import no.westerdals.westbook.responses.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import static no.westerdals.westbook.responses.ResultResponse.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/v1/posts")
public class PostRestController {
    @Autowired
    private PostRepository postRepo;

    @RequestMapping(method = RequestMethod.GET)
    public List<Post> getPosts(@RequestParam(required=false) String parentId,
                               @RequestParam(required=false) String[] tagIds,
                               @RequestParam(defaultValue="20")int maxPosts,
                               @RequestParam(defaultValue="0")int pageIndex) {
        if (maxPosts < 0 || pageIndex < 0)
            return null;
        return postRepo.filterPosts(parentId, tagIds, new PageRequest(pageIndex, maxPosts));
    }

    @RequestMapping(value="/{postId}", method=RequestMethod.GET)
    public Post getPost(@PathVariable String postId) {
        return postRepo.findOne(postId);
    }

    @RequestMapping(value="/by-tags/{tags}", method=RequestMethod.GET)
    public List<Post> getPostsByTag(@PathVariable String[] tags) {
        ArrayList<Post> result = new ArrayList<>();
        result.addAll(postRepo.getByTagIds(tags));
        return result;
    }

    @RequestMapping(value="/by-user/{userId}", method=RequestMethod.GET)
    public List<Post> getPostsByUser(@PathVariable String userId)
    {
        return postRepo.getByUserId(userId);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResultResponse writePost(@RequestBody Post post, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication)principal).getPrincipal();
        post.setTime(new Date());
        Post result = postRepo.insert(post);
        // TODO: Check if user has access to post for this page...
        result.setUserId(userCredentials.getUserId());
        return newOkResult(MessageConstant.POST_CREATED, result);
    }
}
