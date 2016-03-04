package no.westerdals.westbook.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Feed
{
    public Feed(String userId, int index, PostTag[] tags, String[] pageIds)
    {
        this.userId = userId;
        this.index = index;
        this.tags = tags;
        this.pageIds = pageIds;
    }

    @Id
    private String id;
    private String userId;
    private int index;
    private PostTag[] tags;
    // Id to pages
    private String[] pageIds;
}
