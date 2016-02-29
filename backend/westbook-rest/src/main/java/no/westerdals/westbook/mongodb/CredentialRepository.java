package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.Credential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends MongoRepository<Credential, String> {
}
