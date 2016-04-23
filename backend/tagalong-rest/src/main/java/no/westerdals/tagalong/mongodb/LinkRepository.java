package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends MongoRepository<Link, String>
{
}
