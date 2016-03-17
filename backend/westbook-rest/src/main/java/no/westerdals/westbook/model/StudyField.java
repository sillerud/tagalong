package no.westerdals.westbook.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class StudyField
{
    @Id
    private String id;
    private String name;
    private String description;
    private String studyDirection;
}
