package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String>
{
    List<Comment> findByUserId(String userId);
    List<Comment> findByPostId(String postId);
}
