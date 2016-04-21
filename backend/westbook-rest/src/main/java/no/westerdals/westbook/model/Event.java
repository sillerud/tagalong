package no.westerdals.westbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Event {
    @Id
    private String id;
    private String title;
    private String shoutOut;
    private String coverImageId;
    private String ownerId;
    private String pageId;
    private String description;
    private Date startDate;
    private Date endDate;
    private String location;
    private String[] tagIds;
    private String[] attending;
    private String[] galleryImages;

    // Ignored, for html purposes only
    @Transient
    private AccessLevel accessLevel = AccessLevel.READ;
}
