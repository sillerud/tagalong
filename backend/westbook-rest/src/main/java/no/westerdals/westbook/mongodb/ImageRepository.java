package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String>
{
    Image getByUrl(String url);
}
