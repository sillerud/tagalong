package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String>
{
    List<Comment> findByUserId(String userId);
    List<Comment> findByParentId(String parentId);
}
