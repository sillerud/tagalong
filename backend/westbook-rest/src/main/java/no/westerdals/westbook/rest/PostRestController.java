package no.westerdals.westbook.rest;

import no.westerdals.westbook.model.Post;
import no.westerdals.westbook.mongodb.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class PostRestController
{
    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/rest/v1/posts", method = RequestMethod.GET)
    public List<Post> getPosts(@RequestParam(value = "maxPosts", defaultValue = "20")String maxPosts)
    {
        return postRepository.findAll();
    }

    @RequestMapping(value = "/rest/v1/posts/{postId}", method = RequestMethod.GET)
    public Post getPost(@PathVariable String postId)
    {
        return postRepository.findOne(postId);
    }

    @RequestMapping(value = "/rest/v1/posts", method = RequestMethod.POST)
    public String writePost(@RequestBody Post post)
    {
        if (post.getTime() == null)
            post.setTime(new Date());
        postRepository.insert(post);
        return "OK\n";
    }
}
