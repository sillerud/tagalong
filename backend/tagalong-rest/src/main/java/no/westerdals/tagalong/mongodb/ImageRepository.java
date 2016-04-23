package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String>
{
    Image getByUrl(String url);
}
