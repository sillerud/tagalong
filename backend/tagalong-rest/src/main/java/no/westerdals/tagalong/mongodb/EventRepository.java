package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event, String>, EventRepositoryCustom {
}
