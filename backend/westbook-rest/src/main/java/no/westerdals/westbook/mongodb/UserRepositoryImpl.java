package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.User;
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
}
