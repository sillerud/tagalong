package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends MongoRepository<Tag, String> {
    List<Tag> findByDescription(String description, Pageable pageable);
    List<Tag> findByNameLike(String name, Pageable pageable);
    List<Tag> findByParentId(String id);
}
