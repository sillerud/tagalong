package no.westerdals.westbook.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Comment
{
    public Comment(String userId, String parentId, String title,
                   String content, Date timestamp)
    {
        this.userId = userId;
        this.parentId = parentId;
        this.content = content;
        this.timestamp = timestamp;
    }

    @Id
    private String id;
    private String userId;
    private String parentId;
    private String content;
    private Date timestamp;
}
