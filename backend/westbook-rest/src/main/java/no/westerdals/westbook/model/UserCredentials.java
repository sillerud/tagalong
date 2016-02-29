package no.westerdals.westbook.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserCredentials implements UserDetails {
    private final User user;
    private final Credential credential;
    private final List<GrantedAuthority> grantedAuthorities;

    public UserCredentials(User user, Credential credential) {
        this.user = user;
        this.credential = credential;
        if (credential.getAuthorities() == null) {
            grantedAuthorities = new ArrayList<>();
        } else {
            grantedAuthorities = credential.getAuthorities().stream()
                    .map(MongoGrantedAuthority::new)
                    .collect(Collectors.toList());
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

    @Data
    public static class MongoGrantedAuthority implements GrantedAuthority {
        private final String authority;
    }
}
