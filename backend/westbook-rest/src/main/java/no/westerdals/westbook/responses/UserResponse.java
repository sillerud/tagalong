package no.westerdals.westbook.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import no.westerdals.westbook.model.ValueDescription;
import no.westerdals.westbook.model.User;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResponse
{
    private String id;
    private String pageId;
    private String email;
    private String personalInfo;
    private Date born;
    private String studyFieldId;
    private String studyFieldDisplayName;
    private String city;
    private String gender;
    private String firstname;
    private String surname;
    private String profilePictureId;
    private String profileHeaderPictureId;
    private String interests;
    private short yearStarted;
    private ValueDescription[] contactInfo;
    private ValueDescription[] projects;
    private String[] skills;

    public UserResponse(User user)
    {
        this.id = user.getId();
        this.pageId = user.getPageId();
        if (user.isShowEmail())
            this.email = user.getEmail();
        if (user.getBorn() != null)
            this.born = user.getBorn();
        this.personalInfo = user.getPersonalInfo();
        this.studyFieldId = user.getStudyFieldId();
        this.city = user.getCity();
        this.gender = user.getGender();
        this.firstname = user.getFirstname();
        this.surname = user.getSurname();
        this.interests = user.getInterests();
        this.yearStarted = user.getYearStarted();
        this.contactInfo = user.getContactInfo();
        this.projects = user.getProjects();
        this.skills = user.getSkills();
        this.profilePictureId = user.getProfilePictureId();
        this.profileHeaderPictureId = user.getProfileHeaderPictureId();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof UserResponse)
            return getId().equals(((UserResponse) obj).getId());
        return obj instanceof User && getId().equals(((User) obj).getId());
    }
}
