package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String>, PostRepositoryCustom
{
    List<Post> getByUserId(String userId);
    List<Post> getByTagIdsIn(String[] tagId);
    List<Post> getByTagIdsIn(String[] tagId, Pageable pageable);
    List<Post> getByParentId(String pageId);
}
