package no.westerdals.westbook.rest;

import no.westerdals.westbook.MessageConstant;
import no.westerdals.westbook.model.Credential;
import no.westerdals.westbook.model.User;
import no.westerdals.westbook.model.UserCredentials;
import no.westerdals.westbook.mongodb.CredentialRepository;
import no.westerdals.westbook.mongodb.StudyFieldRepository;
import no.westerdals.westbook.mongodb.UserRepository;
import no.westerdals.westbook.responses.ResultResponse;
import no.westerdals.westbook.responses.UserResponse;
import static no.westerdals.westbook.responses.ResultResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/v1/users")
public class UserRestController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private StudyFieldRepository studyFieldRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value="/me", method=RequestMethod.GET)
    public UserResponse getMe(Principal user) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication)user).getPrincipal();
        return resolve(userCredentials.getUser());
    }

    @RequestMapping(value="/{userId}", method=RequestMethod.GET)
    public UserResponse getById(@PathVariable String userId)
    {
        return resolve(userRepository.findOne(userId));
    }


    /*@RequestMapping(method=RequestMethod.PATCH)
    public UserResponse updateUserInfo(@RequestBody User user)
    {
        // This needs some checks if its sane
        if (user.getId() == null)
            return null;
        userRepository.updateStudyField(user.getId(), user.getStudyFieldId());
        return resolve(userRepository.findOne(user.getId()));
    }*/

    @RequestMapping(value="/by-studyfield/{studyField}", method=RequestMethod.GET)
    public List<UserResponse> getByStudyField(@PathVariable String studyField)
    {
        return userRepository.getByStudyFieldId(resolveStudyField(studyField)).stream()
                .map(this::resolve)
                .collect(Collectors.toList());
    }

    @RequestMapping(value="/by-email/{emailAddress}", method=RequestMethod.GET)
    public UserResponse getByEmailAddress(@PathVariable String emailAddress)
    {
        return resolve(userRepository.getByEmail(emailAddress.replaceAll("_", ".")));
    }

    //!!!!!!!!!!NEED TO CHECK ACCESS LEVEL!!!!!!!!!!!!
    @RequestMapping(value="/{userId}", method=RequestMethod.DELETE)
    public ResultResponse deleteUser(@PathVariable String userId) {
        if (userRepository.findOne(userId) == null)
        {
            return newErrorResult(MessageConstant.USER_NOT_FOUND);
        }
        userRepository.delete(userId);
        return newOkResult(MessageConstant.USER_DELETED);
    }

    @RequestMapping(method=RequestMethod.PATCH)
    public ResultResponse updateUser(@RequestBody User user, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication)principal).getPrincipal();
        if (user.getId() == null) {
            user.setId(userCredentials.getUser().getId());
            return newOkResult(MessageConstant.USER_UPDATED, userRepository.update(user));
        } else {
            // TODO: Edit other profiles
            return newErrorResult(MessageConstant.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResultResponse createUser(@RequestBody UserCredentials userCredentials) {
        User user = userCredentials.getUser();
        user.setId(null);
        userRepository.save(user);
        User inserted = userRepository.save(user);
        Credential credential = new Credential(null, userCredentials.getPassword(), !userCredentials.isAccountNonLocked(), userCredentials.getGrantedAuthorities());
        credential.setId(inserted.getId());
        credential.setPasswordHash(passwordEncoder.encode(userCredentials.getPasswordHash()));
        credentialRepository.save(credential);
        return newOkResult(MessageConstant.USER_CREATED, inserted);
    }

    @RequestMapping(value="/me/password", method=RequestMethod.POST)
    public ResultResponse updatePassword(@RequestBody Credential credential) {
        credential.setId(null); // TODO: set to client's id
        credential.setAuthorities(null);
        credential.setAccountLocked(false);
        credentialRepository.save(credential);
        return newOkResult(MessageConstant.USER_UPDATED);
    }

    @PreAuthorize("hasRole('SET_PASSWORD')")
    @RequestMapping(value="/password", method=RequestMethod.POST)
    public ResultResponse setPassword(@RequestBody Credential credential) {
        if (credential.getId() == null)
            return newErrorResult(MessageConstant.USER_NOT_FOUND);

        credential.setPasswordHash(passwordEncoder.encode(credential.getPasswordHash()));

        credentialRepository.save(credential);

        return newOkResult(MessageConstant.USER_UPDATED);
    }

    @RequestMapping(method=RequestMethod.GET)
    public List<UserResponse> getAllUsers()
    {
        return userRepository
                .findAll()
                .stream()
                .map(this::resolve)
                .collect(Collectors.toList());
    }

    public UserResponse resolve(User user)
    {
        if (user == null || user.getId() == null)
            return null;
        UserResponse userResponse = new UserResponse(user);
        if (user.getStudyFieldId() != null)
            userResponse.setStudyFieldDisplayName(studyFieldRepository.findOne(user.getStudyFieldId()).getDescription());
        return userResponse;
    }

    private String resolveStudyField(String studyField)
    {
        return studyFieldRepository.getByName(studyField).getId();
    }
}