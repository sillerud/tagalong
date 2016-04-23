package no.westerdals.tagalong.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.westerdals.tagalong.model.User;

@Data
@NoArgsConstructor
public class CreateUserRequest {
    private String firstname;
    private String surname;
    private String email;
    private String password;
    private String gender;
    private boolean accountLocked;
    private boolean enabled;
    private String[] authorities;

    public User toUser() {
        User user = new User();
        user.setFirstname(firstname);
        user.setSurname(surname);
        user.setEmail(email);
        user.setGender(gender);
        user.setEnabled(enabled);
        return user;
    }
}
