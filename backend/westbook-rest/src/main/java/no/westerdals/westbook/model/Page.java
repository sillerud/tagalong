package no.westerdals.westbook.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Page
{
    public Page(String name, String userId, String contactInfo, PageLink[] links)
    {
        this.name = name;
        this.userId = userId;
        this.contactInfo = contactInfo;
        this.links = links;
    }

    @Id
    private String id;
    private String customUrl;
    private String name;
    private String description;
    private String userId;
    private String contactInfo;
    private String[] tags;
    private PageLink[] links;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class PageLink
    {
        private String url;
        private String description;
    }
}
