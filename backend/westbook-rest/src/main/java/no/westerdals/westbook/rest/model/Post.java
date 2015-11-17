package no.westerdals.westbook.rest.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Post
{
    private final ObjectId _id;
    private final ObjectId pageId;
    private final ObjectId userId;
    private final String content;
    private final Date time;
}
