package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.Event;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface EventRepositoryCustom {
    List<Event> filterEvents(String userId, Date startDate, Date endDate, String[] tagIds, String pageId, Pageable pageable);
    Event attendEvent(String eventId, String userId, boolean attend);
}
