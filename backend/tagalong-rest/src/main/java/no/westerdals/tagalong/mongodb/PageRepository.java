package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends MongoRepository<Page, String> {
    List<Page> getByUserId(String userId);
    List<Page> findByNameLikeIgnoreCase(String name, Pageable pageable);
    Page findByCustomUrl(String url);
}
