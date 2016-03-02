package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends MongoRepository<Card, String> {
    List<Card> findByUserId(String userId);
}
