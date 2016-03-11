package no.westerdals.westbook.rest;

import no.westerdals.westbook.MessageConstant;
import no.westerdals.westbook.model.Post;
import no.westerdals.westbook.mongodb.PostRepository;
import no.westerdals.westbook.responses.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import static no.westerdals.westbook.responses.ResultResponse.*;

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
        for (String tag : tags) {
            result.addAll(postRepo.getByTagsTagId(tag));
        }
        return result;
    }

    @RequestMapping(value="/by-user/{userId}", method=RequestMethod.GET)
    public List<Post> getPostsByUser(@PathVariable String userId)
    {
        return postRepo.getByUserId(userId);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResultResponse writePost(@RequestBody Post post) {
        post.setTime(new Date());
        Post result = postRepo.insert(post);
        return newOkResult(MessageConstant.POST_CREATED, result);
    }
}
