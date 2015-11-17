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
public class Feed
{
    private final ObjectId _id;
    private final ObjectId userId;
    private final int index;
    private final String[] tags;
    private final ObjectId[] pages;
}
