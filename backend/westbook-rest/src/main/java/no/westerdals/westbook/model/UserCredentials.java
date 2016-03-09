package no.westerdals.westbook.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor
@Data
@ToString
public class UserCredentials implements UserDetails {
    private String userId;
    private String email;
    private long accountExpires;
    private boolean accountLocked;
    private boolean enabled;
    private String passwordHash;
    private String[] grantedAuthorities;

    public UserCredentials(User user, Credential credential) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            System.out.println(user);
        }
        this.userId = user.getId();
        System.out.println(credential);
        this.email = user.getEmail();
        this.accountLocked = credential.isAccountLocked();
        this.passwordHash = credential.getPasswordHash();
        if (user.getAccountExpires() != null) {
            this.accountExpires = user.getAccountExpires().getTime();
        }
        if (credential.getAuthorities() == null) {
            grantedAuthorities = new String[0];
        } else {
            grantedAuthorities = credential.getAuthorities();
        }
        System.out.println(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(grantedAuthorities);
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;// !credential.isCredentialsExpired();
    }
}
