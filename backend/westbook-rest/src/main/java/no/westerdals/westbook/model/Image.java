package no.westerdals.westbook.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Image
{
    public Image(String url)
    {
        this.url = url;
    }

    @Id
    private String id;
    private String url;
}
