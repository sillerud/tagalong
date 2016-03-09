package no.westerdals.westbook;

import no.westerdals.westbook.model.Credential;
import no.westerdals.westbook.model.User;
import no.westerdals.westbook.model.UserCredentials;
import no.westerdals.westbook.mongodb.CredentialRepository;
import no.westerdals.westbook.mongodb.UserRepository;
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
        System.out.println(user);
        if (user == null)
            throw new UsernameNotFoundException("Could not find the user '" + s + "'");
        Credential credential = credentialsRepository.findOne(user.getId());
        System.out.println(credential);
        if (credential == null)
            throw new UsernameNotFoundException("Could not find the user '" + s + "' credentials");
        return new UserCredentials(user, credential);
    }
}
