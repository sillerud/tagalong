#Comment API functions
|  REST method |  Short description | http | Example | Returns |
| ------------ | ------------------ | ---- | ------- | ------- |
| /rest/v1/comments/{commentId} | Get comment by id | GET | /rest/v1/comments/565435775c5997185c1c5bd2 | Comment |
| /rest/v1/comments | Create a new comment | POST | /rest/v1/comments | ResultResponse<Comment> |
| /rest/v1/comments/by-post/{postId} | Get a list of comments by post id | GET | /rest/v1/comments/by-post/564c4fc72fa53e0388a940ae | Comment[] |
| /rest/v1/comments/by-user/{userId} | Get a list of comments by user id | GET | /rest/v1/comments/by-user/564c4faf2fa53e0388a940ad | Comment[] |

#JSON format
```json
{
    "id": "565435775c5997185c1c5bd2",
    "userId": "564c4faf2fa53e0388a940ad",
    "parentId": "564c4fc72fa53e0388a940ae",
    "title": "Quiz",
    "content": "Woo quiz!!",
    "date": 1447842568
}
```

| field                 | description                                  |
| --------------------- | -------------------------------------------- |
| id                    | UUID/ObjectId for the comment                |
| userId                | UUID/ObjectId for the user who commented     |
| parentId              | UUID/ObjectId for parent comment/post        |
| title                 | The title for this comment                   |
| content               | The content for this comment                 |
| date                  | The (unix)time for this comment              |

[Information about the mongodb implementation](../db/mongodb_spec.md#comment)
