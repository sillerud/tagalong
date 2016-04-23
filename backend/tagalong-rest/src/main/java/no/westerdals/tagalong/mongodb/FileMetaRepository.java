package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.FileMeta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetaRepository extends MongoRepository<FileMeta, String>
{
}
