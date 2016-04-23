package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.Post;
import no.westerdals.tagalong.model.Upvote;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> filterPosts(String parentId, String[] tagIds, Pageable pageable);
    Post upvotePost(String postId, Upvote upvote);
    Post removePostUpvote(String postId, String userId);
}
