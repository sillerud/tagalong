#Page API functions
|  REST method |  Short description | http | Example | Returns |
| ------------ | ------------------ | ---- | ------- | ------- |
| /rest/v1/pages/{pageId} | Get a page by id | GET | /rest/v1/pages/5656e7b630b630c21c2fa523 | Page |
| /rest/v1/pages/by-user/{userId} | Get a list of pages owned by this user | GET | /rest/v1/pages/564c4fc72fa53e0388a940ae | Page[] |
| /rest/v1/pages | Create a new page | GET | /rest/v1/pages | ResultResponse<Page> |
| /rest/v1/pages | Edit page information | PATCH | /rest/v1/pages | ResultResponse |

#JSON format
```json
{
    "_id": "5656e7b630b630c21c2fa523",
    "userId": "564c4fc72fa53e0388a940ae",
    "title": "User page",
    "contactInfo": "kevin.sillerud@gmail.com",
    "links":
    [
        {
            "description": "github",
            "link": "https://github.com/TheUnnamedDude"
        },
        {
            "description": "twitter",
            "link": "https://twitter.com/TheUnnamedDude"
        }
    ]
}
```

| field                 | description                                  |
| --------------------- | -------------------------------------------- |
| id                    | UUID/ObjectId for the user                   |
| userId                | The user who made this page                  |
| title                 | The title for this page                      |
| contanctInfo          | Prefered contact info                        |
| links                 | A list of link objects                       |

[Information about the mongodb implementation](../db/mongodb_spec.md#page)
