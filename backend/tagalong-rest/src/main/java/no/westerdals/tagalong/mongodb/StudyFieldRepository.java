package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.StudyField;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyFieldRepository extends MongoRepository<StudyField, String> {
    StudyField getByName(String name);
    List<StudyField> getByNameIgnoreCaseLike(String name);
}
