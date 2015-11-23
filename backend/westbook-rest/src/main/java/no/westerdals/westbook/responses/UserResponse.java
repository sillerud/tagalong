package no.westerdals.westbook.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import no.westerdals.westbook.model.User;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResponse
{
    private String id;
    private String pageId;
    private String email;
    private long born;
    private String studyField;
    private String studyFieldDisplayName;
    private String city;
    private String gender;
    private String firstname;
    private String surname;
    private String profilePicture;

    public UserResponse(User user)
    {
        this.id = user.getId();
        this.pageId = user.getPageId();
        if (user.isShowEmail())
            this.email = user.getEmail();
        this.born = user.getBorn().getTime();
        this.studyField = user.getStudyFieldId();
        this.city = user.getCity();
        this.gender = user.getGender();
        this.firstname = user.getFirstname();
        this.surname = user.getSurname();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof UserResponse)
            return getId().equals(((UserResponse) obj).getId());
        if (obj instanceof User)
            return getId().equals(((User) obj).getId());
        return false;
    }
}
