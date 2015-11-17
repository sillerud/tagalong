package no.westerdals.westbook.rest.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Post
{
    @Id
    private final String id;
    private final String pageId;
    private final String userId;
    private final String content;
    private final Date time;
}
