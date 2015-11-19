package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String>
{
    List<Post> getByUserId(String userId);
    List<Post> getByTags(String tag);
    List<Post> getByPageId(String pageId);
}
