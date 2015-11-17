package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PageRepository extends MongoRepository<Page, String>
{
    Page getByUserId(String userId);
    Page getByName(String name);

}
