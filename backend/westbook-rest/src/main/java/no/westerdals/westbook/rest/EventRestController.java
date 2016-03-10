package no.westerdals.westbook.rest;

import no.westerdals.westbook.MessageConstant;
import no.westerdals.westbook.model.Event;
import no.westerdals.westbook.model.UserCredentials;
import no.westerdals.westbook.mongodb.EventRepository;
import no.westerdals.westbook.responses.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import static no.westerdals.westbook.responses.ResultResponse.*;

@RestController
@RequestMapping("/rest/v1/events")
public class EventRestController {
    @Autowired
    private EventRepository eventRepository;

    @RequestMapping
    public List<Event> filterEvents(@RequestParam(required=false, name="startDate") Date startDate,
                                    @RequestParam(required=false, name="endDate") Date endDate,
                                    @RequestParam(required=false, name="tagIds") String[] tagIds,
                                    @RequestParam(required=false, name="pageId") String pageId,
                                    @RequestParam(defaultValue="20", name="entries") int entries,
                                    @RequestParam(defaultValue="0", name="page") int page) {
        // TODO: Add sort
        return eventRepository.filterEvents(startDate, endDate, tagIds, pageId, new PageRequest(page, entries));
    }

    @RequestMapping
    public ResultResponse createEvent(@RequestBody Event event, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication)principal).getPrincipal();
        event.setId(null);
        event.setAttending(new String[]{userCredentials.getUserId()});
        return newOkResult(MessageConstant.EVENT_CREATED, eventRepository.insert(event));
    }
}
