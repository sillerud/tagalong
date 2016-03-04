package no.westerdals.westbook.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Data
public class User implements Serializable {
    public User(String email, Date born, String studyFieldId, String city, String interests,
                String gender, String firstname, String surname, String profilePictureId, ValueDescription[] contactInfo)
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
        this.contactInfo = contactInfo;
    }

    @Id
    private String id;
    private boolean enabled;
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
    private Date accountExpires;
    private ValueDescription contactInfo[];
    private ValueDescription projects[];
    private String[] skills;

    private static final long serialVersionUID = 7123491823762138241L;
}
