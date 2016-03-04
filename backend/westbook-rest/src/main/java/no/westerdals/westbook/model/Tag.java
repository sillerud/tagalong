package no.westerdals.westbook.model;

import lombok.AllArgsConstructor;
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
    public Tag(String parentId, String name) {
        this.parentId = parentId;
        this.name = name;
    }
}
