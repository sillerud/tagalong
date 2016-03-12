package no.tagalong.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Tag {
    private ObjectId id;
    private final ObjectId parent;
    private final String name;
    private final String description;
}
