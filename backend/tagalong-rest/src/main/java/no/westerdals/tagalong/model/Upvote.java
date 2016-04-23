package no.westerdals.tagalong.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Upvote {
    private String userId;
    private StudyDirection studyDirection;
}
