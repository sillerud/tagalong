package no.westerdals.westbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

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
