package no.westerdals.tagalong.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.westerdals.tagalong.model.Feed;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeedResult
{
    private Feed feedInfo;
    private List<FeedPost> feed;
}
