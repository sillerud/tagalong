package no.westerdals.tagalong.rest;

import no.westerdals.tagalong.model.*;
import no.westerdals.tagalong.mongodb.PostRepository;
import no.westerdals.tagalong.mongodb.StudyFieldRepository;
import no.westerdals.tagalong.mongodb.UserRepository;
import no.westerdals.tagalong.responses.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import static no.westerdals.tagalong.responses.ResultResponse.*;
import static no.westerdals.tagalong.MessageConstant.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/v1/posts")
public class PostRestController {
    @Autowired
    private PostRepository postRepo;

    @Autowired
    private StudyFieldRepository studyFieldRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Post> getPosts(@RequestParam(required=false) String[] parentIds,
                               @RequestParam(required=false) String[] tagIds,
                               @RequestParam(defaultValue="20")int maxPosts,
                               @RequestParam(defaultValue="0")int pageIndex) {
        if (maxPosts < 0 || pageIndex < 0)
            return null;
        return postRepo.filterPosts(parentIds, tagIds, new PageRequest(pageIndex, maxPosts, new Sort(Sort.Direction.DESC, "_id")));
    }

    @RequestMapping(value="/{postId}", method=RequestMethod.GET)
    public Post getPost(@PathVariable String postId) {
        return postRepo.findOne(postId);
    }

    // TODO: Garbo code, please redo
    @RequestMapping(value="/{postId}/upvote", method=RequestMethod.POST)
    public ResultResponse<Post> upvote(@PathVariable String postId, @RequestParam(defaultValue="true") boolean upvote, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication)principal).getPrincipal();
        if (upvote) {
            User user = userRepository.findOne(userCredentials.getUserId());
            StudyField studyField = studyFieldRepository.findOne(user.getStudyFieldId());
            Post post = postRepo.findOne(postId);
            if (post.getUpvotes() != null && Arrays.stream(post.getUpvotes())
                    .map(Upvote::getUserId)
                    .anyMatch(userId -> userCredentials.getUserId().equals(userId)))
                return newErrorResult(ALREADY_UPVOTED);
            return newOkResult(UPVOTED, postRepo.upvotePost(postId, new Upvote(userCredentials.getUserId(), studyField.getStudyDirection())));
        } else {
            return newOkResult(UPVOTE_REMOVED, postRepo.removePostUpvote(postId, userCredentials.getUserId()));
        }
    }

    @RequestMapping(value="/{postId}", method=RequestMethod.DELETE)
    public ResultResponse deletePost(@PathVariable String postId, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication)principal).getPrincipal();
        Post post = postRepo.findOne(postId);
        if (post == null)
            return newErrorResult(POST_NOT_FOUND);
        if (!userCredentials.getUserId().equals(post.getUserId()))
            return newErrorResult(ACCESS_DENIED, "Cannot delete posts not owned by you.");
        postRepo.delete(postId);
        return newOkResult(POST_DELETED);
    }

    @RequestMapping(value="/by-user/{userId}", method=RequestMethod.GET)
    public List<Post> getPostsByUser(@PathVariable String userId)
    {
        return postRepo.getByParentId(userId);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResultResponse writePost(@RequestBody Post post, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication)principal).getPrincipal();
        if (post.getContent() == null)
            return newErrorResult(POST_NO_CONTENT_PROVIDED);
        if (post.getTagIds() == null || post.getTagIds().length < 1)
            return newErrorResult(POST_NO_TAGS_PROVIDED);
        if (post.getParentId() == null)
            post.setParentId(userCredentials.getUserId());
        post.setTime(new Date());
        post.setUserId(userCredentials.getUserId());
        Post result = postRepo.insert(post);
        // TODO: Check if user has access to post for this page...
        return newOkResult(POST_CREATED, result);
    }
}
