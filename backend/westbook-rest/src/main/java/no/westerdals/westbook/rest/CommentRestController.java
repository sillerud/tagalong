package no.westerdals.westbook.rest;

import no.westerdals.westbook.MessageConstant;
import no.westerdals.westbook.model.Comment;
import no.westerdals.westbook.mongodb.CommentRepository;
import no.westerdals.westbook.mongodb.PostRepository;
import no.westerdals.westbook.responses.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
}
