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
public class Image
{
    private final ObjectId _id;
    private final String url;
}
