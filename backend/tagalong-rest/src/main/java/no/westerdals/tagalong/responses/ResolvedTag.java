package no.westerdals.tagalong.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResolvedTag {
    private String id;
    private ResolvedTag parent;
    private String name;
    private String description;
}
