package no.westerdals.westbook.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@NoArgsConstructor
@ToString
public class UserCredentials implements UserDetails {
    @Getter
    private User user;
    private boolean accountLocked;
    private String passwordHash;
    @Getter
    private String[] grantedAuthorities;

    public UserCredentials(User user, Credential credential) {
        this.user = user;
        this.accountLocked = credential.isAccountLocked();
        this.passwordHash = credential.getPasswordHash();
        if (credential.getAuthorities() == null) {
            grantedAuthorities = new String[0];
        } else {
            grantedAuthorities = credential.getAuthorities();
        }
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
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountExpires() == null ||
                user.getAccountExpires().after(new Date());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;// !credential.isCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
