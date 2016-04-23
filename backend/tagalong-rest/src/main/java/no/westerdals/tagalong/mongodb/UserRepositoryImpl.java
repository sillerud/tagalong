package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

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
            update.set("studyFieldId", user.getStudyFieldId());
        if (user.getBorn() != null)
            update.set("born", user.getBorn());
        if (user.getAccountExpires() != null)
            update.set("accountExpires", user.getAccountExpires());
        if (user.getCity() != null)
            update.set("city", user.getCity());
        if (user.getContactInfo() != null)
            update.set("contactInfo", user.getContactInfo());
        if (user.getEmail() != null)
            update.set("email", user.getEmail());
        if (user.getFirstname() != null)
            update.set("firstname", user.getFirstname());
        if (user.getSurname() != null)
            update.set("surname", user.getSurname());
        if (user.getInterests() != null)
            update.set("interests", user.getInterests());
        if (user.getPersonalInfo() != null)
            update.set("personalInfo", user.getPersonalInfo());
        if (user.getProfilePictureId() != null)
            update.set("profilePictureId", user.getProfilePictureId());
        if (user.getProfileHeaderPictureId() != null)
            update.set("profileHeaderPictureId", user.getProfileHeaderPictureId());
        return mongoTemplate.findAndModify(new Query(new Criteria("_id").is(user.getId())), update, User.class);
    }
}
