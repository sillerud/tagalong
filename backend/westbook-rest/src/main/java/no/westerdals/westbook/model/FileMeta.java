package no.westerdals.westbook.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import no.westerdals.westbook.ImageType;
import org.springframework.data.annotation.Id;

import java.util.Date;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class FileMeta {
    public FileMeta(String ownerId, String name, ImageType imageType, Date uploadTime) {
        this.ownerId = ownerId;
        this.name = name;
        this.imageType = imageType;
        this.uploadTime = uploadTime;
    }

    @Id
    private String id;
    private String ownerId;
    private String name;
    private ImageType imageType;
    private Date uploadTime;
}
