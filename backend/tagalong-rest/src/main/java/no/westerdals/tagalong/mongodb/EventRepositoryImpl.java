package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.Event;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class EventRepositoryImpl implements EventRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Event> filterEvents(String userId, Date startDate, Date endDate, String[] tagIds, String pageId, Pageable pageable) {
        Query query = new Query();
        if (userId != null) {
            query.addCriteria(where("ownerId").is(userId));
        }
        if (startDate != null) {
            query.addCriteria(where("startDate").gt(startDate));
        }
        if (endDate != null) {
            query.addCriteria(where("endDate").lt(endDate));
        }
        if (tagIds != null && tagIds.length > 0) {
            query.addCriteria(where("tagIds").all((Object[]) tagIds));
        }
        if (pageId != null) {
            query.addCriteria(where("parentId").is(pageId));
        }
        query.with(pageable);
        return mongoTemplate.find(query, Event.class);
    }

    @Override
    public Event attendEvent(String eventId, String userId, boolean attend) {
        Query query = Query.query(where("_id").is(new ObjectId(eventId)));
        Update update = new Update();
        if (attend) {
            update.push("attending", userId);
        } else {
            update.pull("attending", userId);
        }
        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Event.class);
    }


}
