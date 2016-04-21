package no.westerdals.westbook.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Page {
    @Id
    private String id;
    private String logoPictureId;
    private String coverPictureId;
    private String customUrl;
    private String name;
    private String description;
    private String userId;
    private boolean pagePublic;
    private ValueDescription[] contactInfo;
    private String[] tags;
    private PageAdministrator[] administrators;
    private ValueDescription[] links;

    // Ignored, for html purposes only
    @Transient
    private AccessLevel accessLevel = AccessLevel.READ;

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static class PageAdministrator {
        private String userId;
        private AccessLevel accessLevel;
    }
}
