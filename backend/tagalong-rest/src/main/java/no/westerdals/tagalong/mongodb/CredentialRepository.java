package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.Credential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends MongoRepository<Credential, String> {
}
