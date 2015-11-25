#Index
* [User](#user)
* [Post](#post)
* [Comment](#comment)
* [Feed](#feed)
* [Page](#page)
* [Image](#image)

#User
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

#Post
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

#Comment
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

#Feed
```json
{
    "_id": ObjectId,
    "userId": ObjectId,
    "index": int,
    "tags":
    [
        {"tag":String,"displayed":bool},
        {"tag":String,"displayed":bool}
    ],
    "pages": ObjectId[]
}
```

#Page
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

#Image
```json
{
    "_id": ObjectId,
    "url": String
}
```

#StudyField
```json
{
    "_id": ObjectId,
    "name": String,
    "description": String
}
```
