package no.westerdals.tagalong;

import no.westerdals.tagalong.model.Credential;
import no.westerdals.tagalong.model.User;
import no.westerdals.tagalong.model.UserCredentials;
import no.westerdals.tagalong.mongodb.CredentialRepository;
import no.westerdals.tagalong.mongodb.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MongodbUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CredentialRepository credentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(s);
        if (user == null)
            throw new UsernameNotFoundException("Could not find the user '" + s + "'");
        Credential credential = credentialsRepository.findOne(user.getId());
        if (credential == null)
            throw new UsernameNotFoundException("Could not find the user '" + s + "' credentials");
        return new UserCredentials(user, credential);
    }
}
