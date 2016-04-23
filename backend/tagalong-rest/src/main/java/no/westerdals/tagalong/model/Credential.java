package no.westerdals.tagalong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Credential {
    @Id
    String id;
    String passwordHash;
    private boolean accountLocked;
    private String[] authorities;
}
