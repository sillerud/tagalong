package no.westerdals.westbook.rest.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class User
{
    private final ObjectId userId;
    private final String email;
    private final Date born;
    private final ObjectId studyField;
    private final String city;
    private final String interests;
    private final String gender;
    private final String firstname;
    private final String surname;
    private final ObjectId profilePicture;
}
