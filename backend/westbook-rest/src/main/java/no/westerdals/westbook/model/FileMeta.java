package no.westerdals.westbook.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class FileMeta
{
    public FileMeta(String ownerId, String name, boolean attachment, Date uploadTime)
    {
        this.ownerId = ownerId;
        this.name = name;
        this.attachment = attachment;
        this.uploadTime = uploadTime;
    }

    @Id
    private String id;
    private String ownerId;
    private String name;
    private boolean attachment;
    private Date uploadTime;
}
