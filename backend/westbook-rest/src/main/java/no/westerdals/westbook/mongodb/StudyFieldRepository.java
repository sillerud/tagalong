package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.StudyField;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyFieldRepository extends MongoRepository<StudyField, String>
{
    StudyField getByName(String name);
}
