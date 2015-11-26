#Link API functions
|  REST method |  Short description | http | Example | Returns |
| ------------ | ------------------ | ---- | ------- | ------- |
| /rest/v1/link/{linkId} | Get a link object by link-id | GET | /rest/v1/link/5656df9d30b630c21c2fa522 | Link |
| /rest/v1/link | Create a new link | POST | /rest/v1/link | ResultResponse<Link> |

#JSON format
```json
{
    "id": "5656df9d30b630c21c2fa522",
    "url": "https://google.com/",
    "userId": "564c4fc72fa53e0388a940ae"
}
```

| field                 | description                                  |
| --------------------- | -------------------------------------------- |
| id                    | UUID/ObjectId for the url                    |
| name                  | The url itself                               |
| userId                | The user who posted this url                 |

[Information about the mongodb implementation](../db/mongodb_spec.md#link)
