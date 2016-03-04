package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends MongoRepository<Page, String> {
    List<Page> getByUserId(String userId);
    List<Page> findByName(String name, Pageable pageable);
    Page findByCustomUrl(String url);
}
