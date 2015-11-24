
#Post API functions
|  REST method                      |  Short description                       | http | Example                                           | Returns |
| --------------------------------- | ---------------------------------------- | ------------------------------------------------- | ------ | ---- |
| /rest/v1/posts/{postid}           | get a post by postid                     | GET  | /rest/v1/posts/564c4fc72fa53e0388a940ae | Post   |
| /rest/v1/posts?limit={limit}      | get all post limited by param            | GET  | /rest/v1/posts?limit=20                           | Post[] |
| /rest/v1/posts/by-tags/{tags}     | get a list of posts by tags              | GET  | /rest/v1/posts/by-tag/#social.fubar,#dev.java     | Post[] |
| /rest/v1/posts/by-userid/{userid} | get a list of posts by userid            | GET  | /rest/v1/posts/by-userid/564c4faf2fa53e0388a940ad | Post[] |
| /rest/v1/posts/by-page/{pageid}   | get a list of posts by pageid            | GET  | /rest/v1/posts/by-pageid/564c505b2fa53e0388a940af | Post[] |
| /rest/v1/posts                    | write a new post                         | POST | /rest/v1/posts                                    | TBD    |
| /rest/v1/posts                    | edit title and content                   | PUT  | /rest/v1/posts                                    | TBD    |

#JSON format
```json
{
    "id": "564c4fc72fa53e0388a940ae",
    "pageId": "564c505b2fa53e0388a940af",
    "userId": "564c4faf2fa53e0388a940ad",
    "title": "Quiz",
    "content": "Java quiz i fubar! Ta med PC og mat",
    "shortDescription": "Java quiz i fubar!",
    "tags":
    [
        "#social.fubar",
        "#dev.java"
    ],
    "date": 1447842568
}
```

| field            | description                                 |
| ---------------- | ------------------------------------------- |
| id               | UUID/ObjectId for the post                  |
| pageId           | UUID/ObjectId for the page it was posted on |
| userId           | UUID/ObjectId for the user that posted it   |
| title            | The title for this post                     |
| content          | The content of this post                    |
| shortDescription | A short summary of the post for the cards   |
| tags             | A array of tags in the post                 |
| date             | Unix timestamp from when it was posted      |

[Information about the mongodb implementation](../db/mongodb_spec.md#post)
