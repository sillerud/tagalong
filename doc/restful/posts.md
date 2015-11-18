
#Post API functions#
|  rest method                      |  short description            | Example                                           | Returns |
| --------------------------------- | ----------------------------- | ------------------------------------------------- | ------ |
| /rest/v1/posts/{postid}           | get a post by postid          | /rest/v1/posts/by-postid/564c4fc72fa53e0388a940ae | Post   |
| /rest/v1/posts?limit={limit}      | get all post limited by param | /rest/v1/posts?limit=20                           | Post[] |
| /rest/v1/posts/by-tags/{tags}     | get a list of posts by tags   | /rest/v1/posts/by-tag/#social.fubar,#dev.java     | Post[] |
| /rest/v1/posts/by-userid/{userid} | get a list of posts by userid | /rest/v1/posts/by-userid/564c4faf2fa53e0388a940ad | Post[] |
| /rest/v1/posts/by-page/{pageid}   | get a list of posts by pageid | /rest/v1/posts/by-pageid/564c505b2fa53e0388a940af | Post[] |

#JSON format#
```json
{
    "id": "564c4fc72fa53e0388a940ae",
    "pageId": "564c505b2fa53e0388a940af",
    "userId": "564c4faf2fa53e0388a940ad",
    "content": "Java quiz i fubar!",
    "tags":
    [
        "#social.fubar",
        "#dev.java"
    ],
    "date": 1447842568
}
```

| field   | description                                 |
| ------- | ------------------------------------------- |
| id      | UUID/ObjectId for the post                  |
| pageId  | UUID/ObjectId for the page it was posted on |
|

[Information about the mongodb implementation](../mongodb_spec.md)
