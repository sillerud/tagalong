package no.westerdals.westbook.rest;

import no.westerdals.westbook.MessageConstant;
import no.westerdals.westbook.model.Comment;
import no.westerdals.westbook.model.UserCredentials;
import no.westerdals.westbook.mongodb.CommentRepository;
import no.westerdals.westbook.mongodb.PostRepository;
import no.westerdals.westbook.responses.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import static no.westerdals.westbook.responses.ResultResponse.*;

@RestController
@RequestMapping("/rest/v1/comments")
public class CommentRestController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @PreAuthorize("hasRole('COMMENT')")
    @RequestMapping(method=RequestMethod.POST)
    public ResultResponse createComment(@RequestBody Comment comment, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication) principal).getPrincipal();
        if (comment.getParentId() == null || postRepository.findOne(comment.getParentId()) == null)
            return newErrorResult(MessageConstant.POST_NOT_FOUND);
        // TODO: check if the user can access this post
        comment.setId(null);
        comment.setUserId(userCredentials.getUserId());
        comment.setTimestamp(new Date());
        Comment result = commentRepository.save(comment);
        return newOkResult(MessageConstant.COMMENT_CREATED, result);
    }

    @RequestMapping(value="/{commentId}", method=RequestMethod.GET)
    public Comment getComment(@PathVariable String commentId)
    {
        return commentRepository.findOne(commentId);
    }

    @RequestMapping(value="/by-post/{postId}", method=RequestMethod.GET)
    public List<Comment> getCommentByPost(@PathVariable String postId) {
        return commentRepository.findByParentId(postId);
    }

    @RequestMapping(value="/by-user/{userId}", method=RequestMethod.GET)
    public List<Comment> getCommentsByUser(@PathVariable String userId) {
        return commentRepository.findByUserId(userId);
    }

    @RequestMapping(value="/{commentId}", method=RequestMethod.DELETE)
    public ResultResponse deleteComment(@PathVariable String commentId, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication) principal).getPrincipal();
        Comment comment = commentRepository.findOne(commentId);
        if (comment == null || !comment.getUserId().equals(userCredentials.getUserId()))
            return newErrorResult(MessageConstant.ACCESS_DENIED);
        commentRepository.delete(comment);
        return newOkResult(MessageConstant.COMMENT_DELETED);
    }

}
