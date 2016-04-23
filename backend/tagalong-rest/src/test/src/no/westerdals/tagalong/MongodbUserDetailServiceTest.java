package no.westerdals.tagalong;

import no.westerdals.tagalong.model.Credential;
import no.westerdals.tagalong.model.User;
import no.westerdals.tagalong.model.UserCredentials;
import no.westerdals.tagalong.mongodb.CredentialRepository;
import no.westerdals.tagalong.mongodb.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MongodbUserDetailServiceTest {
    @InjectMocks
    private MongodbUserDetailService userDetailService = new MongodbUserDetailService();
    @Mock
    private UserRepository userRepository;
    @Mock
    private CredentialRepository credentialRepository;
    private Date date = new Date(0);
    private User user = new User("mail@somedomain", date, "programming", "Nowhere", "Java", "None", "Firstname", "Surname", null, null);
    private Credential credential = new Credential("1234", "passwordhashhere", false, new String[0]);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void mockUser() {
        when(userRepository.getByEmail(anyString())).thenReturn(user);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testNoUserThrowsException() throws Exception {
        userDetailService.loadUserByUsername("mail@somedomain");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testUserWithoutCredentialsThrowsException() throws Exception {
        mockUser();
        userDetailService.loadUserByUsername("mail@somedomain");
    }

    @Test
    public void testReturnsUserAndCredentials() throws Exception {
        mockUser();
        when(credentialRepository.findOne(user.getId())).thenReturn(credential);
        UserCredentials userCredentials = (UserCredentials) userDetailService.loadUserByUsername("mail@somedomain");
        assertEquals(user.getEmail(), userCredentials.getEmail());
        assertEquals(credential.getPasswordHash(), userCredentials.getPasswordHash());
    }
}