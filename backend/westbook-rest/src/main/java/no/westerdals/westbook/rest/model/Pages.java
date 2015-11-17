package no.westerdals.westbook.rest.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Pages
{
    private final ObjectId _id;
    private final ObjectId userId;
    private final String contactInfo;
    private final Link[] links;
}
