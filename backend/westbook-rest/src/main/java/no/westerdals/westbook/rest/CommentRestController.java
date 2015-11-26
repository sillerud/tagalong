package no.westerdals.westbook.rest;

import no.westerdals.westbook.MessageConstant;
import no.westerdals.westbook.model.Comment;
import no.westerdals.westbook.mongodb.CommentRepository;
import no.westerdals.westbook.mongodb.PostRepository;
import no.westerdals.westbook.responses.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static no.westerdals.westbook.responses.ResultResponse.*;

@RestController
@RequestMapping("/rest/v1/comments")
public class CommentRestController
{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @RequestMapping(method=RequestMethod.POST)
    public ResultResponse createComment(@RequestBody Comment comment)
    {
        if (postRepository.findOne(comment.getParentId()) == null)
            return newErrorResult(MessageConstant.POST_NOT_FOUND);
        // TODO: check if the user can access this post
        comment.setId(null);
        comment.setTimestamp(new Date());
        Comment result = commentRepository.insert(comment);
        return newOkResult(MessageConstant.COMMENT_CREATED, result);
    }

    @RequestMapping(value="/{commentId}", method=RequestMethod.GET)
    public Comment getComment(@PathVariable String commentId)
    {
        return commentRepository.findOne(commentId);
    }

    @RequestMapping(value="/by-posts/{postId}", method=RequestMethod.GET)
    public List<Comment> getCommentByPost(@PathVariable String postId)
    {
        return commentRepository.findByParentId(postId);
    }

    @RequestMapping(value="/by-user/{userId}", method=RequestMethod.GET)
    public List<Comment> getCommentsByUser(@PathVariable String userId)
    {
        return commentRepository.findByUserId(userId);
    }
}
