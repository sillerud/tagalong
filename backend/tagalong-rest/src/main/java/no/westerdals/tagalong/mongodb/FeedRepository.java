package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.Feed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends MongoRepository<Feed, String>
{
    Feed getByIndex(String userId, int index);
}
