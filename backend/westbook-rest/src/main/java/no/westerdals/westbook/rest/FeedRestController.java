package no.westerdals.westbook.rest;

import no.westerdals.westbook.MessageConstant;
import no.westerdals.westbook.model.Feed;
import no.westerdals.westbook.model.Post;
import no.westerdals.westbook.model.User;
import no.westerdals.westbook.mongodb.FeedRepository;
import no.westerdals.westbook.mongodb.PostRepository;
import no.westerdals.westbook.mongodb.UserRepository;
import no.westerdals.westbook.responses.FeedPost;
import no.westerdals.westbook.responses.FeedResult;
import no.westerdals.westbook.responses.ResultResponse;
import static no.westerdals.westbook.responses.ResultResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/v1/feeds")
public class FeedRestController
{
    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    private final static Sort DATE_SORT = new Sort(Sort.Direction.DESC, "date");

    private final static Comparator<FeedPost> POST_COMPARATOR = (p1, p2) -> {
        if (p1.getTime().getTime() > p2.getTime().getTime())
            return 1;
        else if (p1.getTime().getTime() < p2.getTime().getTime())
            return -1;
        else
            return 0;
    };

    @RequestMapping(method=RequestMethod.GET)
    public List<FeedResult> getFeedCards() {
        return feedRepository.findAll(new PageRequest(0, 10))
                .getContent()
                .stream()
                .map(this::resolve)
                .collect(Collectors.toList());
    }

    @RequestMapping(value="/{feedId}", method=RequestMethod.GET)
    public FeedResult getFeedPosts(@PathVariable String feedId, @RequestParam(defaultValue = "1") int page) throws Exception {
        return resolve(feedRepository.findOne(feedId), page);
    }

    @RequestMapping(value="/{feedId}", method=RequestMethod.DELETE)
    public ResultResponse deleteFeed(@PathVariable String feedId) {
        feedRepository.delete(feedId);
        return newOkResult(MessageConstant.FEED_DELETED);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResultResponse createFeed(@RequestBody Feed feed) {
        feed.setId(null);
        Feed result = feedRepository.insert(feed);
        return newOkResult(MessageConstant.FEED_CREATED, result);
    }

    private FeedResult resolve(Feed feed) {
        return resolve(feed, 0);
    }

    private FeedResult resolve(Feed feed, int page) {
        List<FeedPost> posts = new ArrayList<>();
        List<Post> dbPosts = postRepository.getByTagIds(feed.getTagIds(), new PageRequest(page, 10, DATE_SORT));
        posts.addAll(dbPosts.stream().map(this::resolve).collect(Collectors.toList()));

        Collections.sort(posts, POST_COMPARATOR);

        if (posts.size() > 10) {
            posts = posts.subList(0, 10);
        }

        return new FeedResult(feed, posts);
    }

    private FeedPost resolve(Post post) {
        User user = userRepository.findOne(post.getUserId());
        user.setEmail(null);
        return new FeedPost(post, user);
    }


}
