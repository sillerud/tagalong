package no.westerdals.westbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TagAlong {
    private String userId;
    private StudyDirection studyDirection;
}
