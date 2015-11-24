package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.FileMeta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetaRepository extends MongoRepository<FileMeta, String>
{
}
