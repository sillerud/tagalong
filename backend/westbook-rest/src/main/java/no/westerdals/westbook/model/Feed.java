package no.westerdals.westbook.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Feed
{
    public Feed(String userId, int index, String[] tagIds, String[] pageIds)
    {
        this.userId = userId;
        this.index = index;
        this.tagIds = tagIds;
        this.pageIds = pageIds;
    }

    @Id
    private String id;
    private String userId;
    private int index;
    private String[] tagIds;
    // Id to pages
    private String[] pageIds;
}
