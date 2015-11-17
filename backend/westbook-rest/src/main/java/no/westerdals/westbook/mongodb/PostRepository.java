package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String>
{
    List<Post> getByUserId(String userId);
    List<Post> getByTags(String tag);
    List<Post> getByPage(String pageId);
}
