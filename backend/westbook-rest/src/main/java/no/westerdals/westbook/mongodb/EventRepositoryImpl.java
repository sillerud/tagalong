package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class EventRepositoryImpl implements EventRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Event> filterEvents(Date startDate, Date endDate, String[] tagIds, String pageId, Pageable pageable) {
        Query query = new Query();
        if (startDate != null) {
            query.addCriteria(where("startDate").gt(startDate));
        }
        if (endDate != null) {
            query.addCriteria(where("endDate").lt(endDate));
        }
        if (tagIds != null && tagIds.length > 0) {
            query.addCriteria(where("tagIds").all(tagIds));
        }
        if (pageId != null) {
            query.addCriteria(where("pageId").is(pageId));
        }
        query.with(pageable);
        return mongoTemplate.find(query, Event.class);
    }
}
