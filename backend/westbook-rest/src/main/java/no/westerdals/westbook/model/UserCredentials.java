package no.westerdals.westbook.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
public class UserCredentials implements UserDetails {
    @Getter
    private User user;
    @Getter
    private Credential credential;
    private List<GrantedAuthority> grantedAuthorities;

    public UserCredentials(User user, Credential credential) {
        this.user = user;
        this.credential = credential;
        if (credential.getAuthorities() == null) {
            grantedAuthorities = new ArrayList<>();
        } else {
            grantedAuthorities = AuthorityUtils.createAuthorityList(credential.getAuthorities()
                    .toArray(new String[credential.getAuthorities().size()]));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return credential.getPasswordHash();
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
        return !credential.isAccountLocked();
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
