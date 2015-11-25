#User API functions
|  REST method |  Short description | http | Example | Returns |
| ------------ | ------------------ | ---- | ------- | ------- |
| /rest/v1/uploads/{uploadId} |  | GET | /rest/v1/comments/5654552f93f4c50095f4999c | A stream with the content |
| /rest/v1/uploads/ | Upload content | POST | /rest/v1/uploads | ResultResponse<FileMeta> |
| /rest/v1/uploads/ | Get all file info from meta db | /rest/v1/uploads | FileMeta[] |
| /rest/v1/uploads/meta/{uploadId} | Get a list of comments by post id | GET | /rest/v1/uploads/meta/5654552f93f4c50095f4999c | FileMeta |

#JSON format
```json
{
    "id": "5654552f93f4c50095f4999c",
    "name": "Parrot.gif",
    "attachment": false,
    "uploadTime":
}
```

| field                 | description                                  |
| --------------------- | -------------------------------------------- |
| id                    | UUID/ObjectId for the upload                 |
| name                  | The filename                                 |
| attachment            | boolean, should it be handled as a attachment? |
| uploadTime            | When the image was uploaded                  |
