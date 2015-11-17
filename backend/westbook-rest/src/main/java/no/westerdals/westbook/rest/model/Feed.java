package no.westerdals.westbook.rest.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Feed
{
    @Id
    private final String id;
    private final String userId;
    private final int index;
    private final String[] tags;
    // Id to pages
    private final String[] pageIds;
}
