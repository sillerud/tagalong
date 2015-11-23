package no.westerdals.westbook.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class User
{
    public User(String email, Date born, String studyFieldId, String city, String interests,
                String gender, String firstname, String surname, String profilePictureId)
    {
        this.email = email;
        this.born = born;
        this.studyFieldId = studyFieldId;
        this.city = city;
        this.interests = interests;
        this.gender = gender;
        this.firstname = firstname;
        this.surname = surname;
        this.profilePictureId = profilePictureId;
    }

    @Id
    private String id;
    private String pageId;
    private String email;
    private boolean showEmail;
    private Date born;
    private String studyFieldId;
    private String city;
    private String interests;
    private String gender;
    private String firstname;
    private String surname;
    private String profilePictureId;
}
