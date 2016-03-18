package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.Post;
import no.westerdals.westbook.model.Upvote;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> filterPosts(String parentId, String[] tagIds, Pageable pageable);
    Post tagAlongToPost(String postId, Upvote upvote);
}
