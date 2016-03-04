package no.westerdals.westbook.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Data
public class Tag {
    @Id
    String id;
    String parentId;
    String name;
    String description;
    public Tag(String parentId, String name, String description) {
        this.parentId = parentId;
        this.name = name;
        this.description = description;
    }
}
