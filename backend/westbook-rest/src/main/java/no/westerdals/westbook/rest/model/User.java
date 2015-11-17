package no.westerdals.westbook.rest.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class User
{
    @Id
    private final String id;
    private final String email;
    private final Date born;
    private final String studyFieldId;
    private final String city;
    private final String interests;
    private final String gender;
    private final String firstname;
    private final String surname;
    private final String profilePictureId;
}
