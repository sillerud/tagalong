#User#
```json
{
    "_id": ObjectId,
    "email": String,
    "born": Date,
    "studyField": ObjectId,
    "city": String,
    "interests": String,
    "gender": String,
    "firstname": String,
    "surname": String,
    "profilePicture": ObjectId
}
```

#Post#
```json
{
    "_id": ObjectId,
    "pageId": ObjectId,
    "userId": ObjectId,
    "title": String,
    "content": String,
    "time": timestamp
}
```

#Comment#
```json
{
    "_id": ObjectId,
    "userId": ObjectId,
    "parentId": ObjectId,
    "title": String,
    "content": String,
    "time": timestamp
}
```

#Feed#
```json
{
    "_id": ObjectId,
    "userId": ObjectId,
    "index": int,
    "tags": String[],
    "pages": ObjectId[]
}
```

#Page#
```json
{
    "_id": ObjectId,
    "userId": ObjectId,
    "title": String,
    "contactInfo": String,
    "links":
    [
        {
            "description": String,
            "link": String
        }
    ]
}
```

#Image#
```json
{
    "_id": ObjectId,
    "url": String
}
```
