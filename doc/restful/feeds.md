#Feed API functions
|  REST method |  Short description | http | Example | Returns |
| ------------ | ------------------ | ---- | ------- | ------- |
| /rest/v1/feeds | Get a list of feed objects | GET | /rest/v1/feeds | FeedResult[] |
| /rest/v1/feeds | Create a new feed | POST | /rest/v1/feeds | ResultResponse<FeedObject> |
| /rest/v1/feeds/{feedId} | Delete a feed | DELETE | /rest/v1/feeds/56558a8830b630c21c2fa51f | TBD |
| /rest/v1/feeds/{feedId} | Get a feed object | GET | /rest/v1/feeds/56558a8830b630c21c2fa51f | FeedObject |

#JSON format (feed result)
```json
{
    "feedInfo": "feed object, see JSON format (feed object) bellow",
    "feed": "array of posts, see JSON format (feed post) bellow"
}
```

#JSON format (feed object)
```json
{
    "_id": "56558a8830b630c21c2fa51f",
    "userId": "564c4faf2fa53e0388a940ad",
    "index": 0,
    "tags": [
        {"name": "fubar", "displayed": true},
        {"name": "social", "displayed": true}
    ],
    "pages": ["56558bcb30b630c21c2fa520", "56558c0430b630c21c2fa521"]
}
```

#JSON format (feed post)
```json
{
    "_id": "564c4fc72fa53e0388a940ae",
    "title": "Quiz",
    "summary": "Java quiz i fubar!",
    "time": 1448447521000,
    "commentCount": 21
}
```

#Feed object
| field                 | description                                  |
| --------------------- | -------------------------------------------- |
| id                    | UUID/ObjectId for this feed                  |
| userId                | The user id of the person who posted this    |
| index                 | The index of this feed                       |
| tags                  | A array of tags for this feed                |
| pages                 | A array of posts for this feed               |

#Feed post
| field                 | description                                  |
| --------------------- | -------------------------------------------- |
| id                    | UUID/ObjectId for post                       |
| title                 | The title for this post                      |
| summary               | Short summary of the post content            |
| uploadTime            | When the image was uploaded                  |

[Information about the mongodb implementation](../db/mongodb_spec.md#feed)
