#User API functions
|  REST method |  Short description | http | Example | Returns |
| ------------ | ------------------ | ---- | ------- | ------- |
| /rest/v1/users/{userId} | get a post by userId | GET | /rest/v1/users/564c4fc72fa53e0388a940ae | User |
| /rest/v1/users | Get all users (should only be admin-only) | GET | /rest/v1/users | User[] |
| /rest/v1/users/by-name/{nameString} | Get a list of possible users by name | GET | /rest/v1/users/by-name/Kevin%20Sillerud | User[] |
| /rest/v1/users/by-studyfield/{studyField} | Get a list of users by study field | GET | /rest/v1/users/by-studyfield/programming-woact | User[] |
| /rest/v1/users/by-email/{email}  | Get a list of users by email(admin-only) | GET | /rest/v1/users/by-email/silkev14@student.westerdals.no | User[] |

#JSON format
```json
{
    "id": "564c4faf2fa53e0388a940ad",
    "pageId": "564c505b2fa53e0388a940af",
    "email": "silkev14@student.westerdals.no",
    "born": 1447842568,
    "studyField": "564c4fc72fa53e0388a940ae",
    "studyFieldDisplayName": "Programming Westerdals Oslo ACT",
    "city": "Lillestrøm",
    "interests": "Java, taco og spill",
    "gender": "Male",
    "firstname": "Kevin",
    "surname": "Sillerud",
    "profilePicture": "/images/5652eaf7acf248fd258299e7.png"
}
```

| field                 | description                                  |
| --------------------- | -------------------------------------------- |
| id                    | UUID/ObjectId for the user                   |
| pageId                | UUID/ObjectId for the users description posted on |
| email                 | The users email-address(can be null)         |
| born                  | The date this person was born (UNIX time)    |
| studyField            | What the student is studying (id)            |
| studyFieldDisplayName | What's displayed to the user                 |
| city                  | Optional address for this user(can be null)  |
| interests             | A string of interests, entirely user defined |
| gender                | String representation of gender              |
| firstname             | The users first name                         |
| surname               | The users surname                            |
| profilePictureUrl     | The url to this users profile picture        |

[Information about the mongodb implementation](../db/mongodb_spec.md#post)
