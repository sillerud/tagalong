#User API functions
|  REST method |  Short description | http | Example | Returns |
| ------------ | ------------------ | ---- | ------- | ------- |
| /rest/v1/static/studyfield/{studyfiedId} | Get study field info by id | GET | /rest/v1/static/studyfield/565313aa180cc331da4856d4 | StudyField |
| /rest/v1/static/studyfield/by-name/{name} | Get study field info by name | GET | /rest/v1/static/studyfield/woact.programming | StudyField |
| /rest/v1/static/studyfield | Add a new study field | POST | StudyField |

#JSON format(studyfield)
```json
{
    "id": "565313aa180cc331da4856d4",
    "name": "woact.programming",
    "description": "Programming"
}
```

| field                 | description                                  |
| --------------------- | -------------------------------------------- |
| id                    | UUID/ObjectId for the study field            |
| name                  | Human readable id for changing full name     |
| description           | Full name for this study field               |

[Information about the mongodb implementation](../db/mongodb_spec.md#studyfield)
