package no.westerdals.tagalong.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.westerdals.tagalong.model.Post;
import no.westerdals.tagalong.model.User;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeedPost
{
    public FeedPost(Post post, User user)
    {
        this.title = post.getTitle();
        if (post.getContent().length() > 255)
        {
            this.summary = post.getContent().substring(0, 252) + "...";
        }
        else
        {
            this.summary = post.getContent();
        }
        this.user = user;
        this.time = post.getTime();
    }
    private User user;
    private String title;
    private String summary;
    private Date time;
    private long commentCount;
}
