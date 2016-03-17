package no.westerdals.westbook.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@NoArgsConstructor
@Data
public class Post {
    @Id
    private String id;
    private String parentId;
    private String userId;
    private String title;
    private String content;
    private String shortDescription;
    private String[] tagIds;
    private TagAlong[] tagAlongs;
    private Date time;
}
