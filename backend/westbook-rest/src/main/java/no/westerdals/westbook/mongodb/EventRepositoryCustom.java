package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.Event;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface EventRepositoryCustom {
    List<Event> filterEvents(Date startDate, Date endDate, String[] tagIds, String pageId, Pageable pageable);
}
