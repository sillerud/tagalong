package no.westerdals.westbook.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Link
{
    public Link(String description, String url)
    {
        this.description = description;
        this.url = url;
    }
    @Id
    private String id;
    private String description;
    private String url;
}
