package no.westerdals.westbook.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Link
{
    public Link(String url, String userId)
    {
        this.url = url;
        this.userId = userId;
    }
    @Id
    private String id;
    private String url;
    private String userId;
}
