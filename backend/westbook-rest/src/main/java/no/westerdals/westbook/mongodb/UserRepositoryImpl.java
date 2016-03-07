package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom
{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateStudyField(String id, String studyFieldId)
    {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.updateFirst(query, Update.update("studyFieldId", studyFieldId), User.class);
    }

    @Override
    public User update(User user) {
        if (user.getId() == null)
            return null;
        Update update = new Update();
        if (user.getStudyFieldId() != null)
            update.addToSet("studyFieldId", user.getStudyFieldId());
        if (user.getBorn() != null)
            update.addToSet("born", user.getBorn());
        if (user.getAccountExpires() != null)
            update.addToSet("accountExpires", user.getAccountExpires());
        if (user.getCity() != null)
            update.addToSet("city", user.getCity());
        if (user.getContactInfo() != null)
            update.addToSet("contactInfo", user.getContactInfo());
        if (user.getEmail() != null)
            update.addToSet("email", user.getEmail());
        if (user.getFirstname() != null)
            update.addToSet("firstname", user.getEmail());
        if (user.getSurname() != null)
            update.addToSet("surname", user.getSurname());
        if (user.getInterests() != null)
            update.addToSet("interests", user.getInterests());
        if (user.getProfilePictureId() != null)
            update.addToSet("profilePictureId", user.getProfilePictureId());
        if (user.getProfileHeaderPictureId() != null)
            update.addToSet("profileHeaderPictureId", user.getProfileHeaderPictureId());
        ObjectId id = (ObjectId) mongoTemplate.updateFirst(new Query(new Criteria("_id").is(user.getId())), update, User.class).getUpsertedId();
        return mongoTemplate.findOne(new Query(new Criteria("_id").is(id)), User.class);
    }
}
