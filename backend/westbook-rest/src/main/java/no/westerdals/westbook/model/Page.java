package no.westerdals.westbook.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Page
{
    public Page(String name, String userId, String contactInfo, Link[] links)
    {
        this.name = name;
        this.userId = userId;
        this.contactInfo = contactInfo;
        this.links = links;
    }

    @Id
    private String id;
    private String name;
    private String userId;
    private String contactInfo;
    private Link[] links;
}
