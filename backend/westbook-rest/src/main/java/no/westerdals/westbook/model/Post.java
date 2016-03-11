package no.westerdals.westbook.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@NoArgsConstructor
@Data
public class Post
{
    public Post(String pageId, String userId, String title, String content, String[] tags, Date time) {
        this.parentId = pageId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.time = time;
    }
    @Id
    private String id;
    private String parentId;
    private String userId;
    private String title;
    private String content;
    private String shortDescription;
    private String[] tags;
    private Date time;
}
