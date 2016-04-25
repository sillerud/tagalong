package no.westerdals.tagalong.rest;

import no.westerdals.tagalong.MessageConstant;
import no.westerdals.tagalong.ModelHelper;
import no.westerdals.tagalong.model.AccessLevel;
import no.westerdals.tagalong.model.Event;
import no.westerdals.tagalong.model.UserCredentials;
import no.westerdals.tagalong.mongodb.EventRepository;
import no.westerdals.tagalong.responses.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import static no.westerdals.tagalong.responses.ResultResponse.*;

@RestController
@RequestMapping("/rest/v1/events")
public class EventRestController {
    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(method=RequestMethod.GET)
    public List<Event> filterEvents(@RequestParam(required=false, name="startDate") Date startDate,
                                    @RequestParam(required=false, name="endDate") Date endDate,
                                    @RequestParam(required=false, name="tagIds") String[] tagIds,
                                    @RequestParam(required=false, name="pageId") String pageId,
                                    @RequestParam(defaultValue="20", name="entries") int entries,
                                    @RequestParam(defaultValue="0", name="page") int page,
                                    @RequestParam(required=false, name="createdBy") String createdBy,
                                    @RequestParam(defaultValue="startDate", name="orderBy") String orderBy,
                                    Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication)principal).getPrincipal();
        // TODO: Add sort
        if (createdBy != null && createdBy.equals("me"))
            createdBy = userCredentials.getUserId();
        return eventRepository.filterEvents(createdBy, startDate, endDate, tagIds, pageId, new PageRequest(page, entries, getOrder(orderBy)));
    }

    private Sort getOrder(String orderBy) {
        switch (orderBy) {
            case "startDate":
                return new Sort(Sort.Direction.ASC, "startDate");
            case "peopleAttending":
                return new Sort(Sort.Direction.DESC, "attending");
            case "tags":
                return new Sort(Sort.Direction.DESC, "_id"); // TODO: Add order by tagalongs
            default:
                return new Sort(Sort.Direction.DESC, "_id");
        }
    }

    @RequestMapping(value="/{eventId}",method=RequestMethod.GET)
    public Event getEventById(@PathVariable String eventId, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication)principal).getPrincipal();
        Event event = eventRepository.findOne(eventId);
        event.setAccessLevel(getAccessLevel(event, userCredentials.getUserId()));
        return event;
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResultResponse createEvent(@RequestBody Event event, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication)principal).getPrincipal();
        event.setId(null);
        event.setAttending(new String[]{userCredentials.getUserId()});
        event.setOwnerId(userCredentials.getUserId());
        return newOkResult(MessageConstant.EVENT_CREATED, eventRepository.insert(event));
    }

    @RequestMapping(method=RequestMethod.PATCH)
    public ResultResponse updateEvent(@RequestBody Event event, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication)principal).getPrincipal();
        Event original = eventRepository.findOne(event.getId());
        if (original == null)
            return newErrorResult(MessageConstant.EVENT_NOT_FOUND);
        if (!original.getOwnerId().equals(userCredentials.getUserId()))
            return newErrorResult(MessageConstant.ACCESS_DENIED, "You do not have access to modify this event.");
        return newOkResult(MessageConstant.EVENT_UPDATED, eventRepository.save(ModelHelper.mapObjects(original, event, Event.class)));
    }

    @RequestMapping(value="/{eventId}", method=RequestMethod.DELETE)
    public ResultResponse deleteEvent(@PathVariable String eventId, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication)principal).getPrincipal();
        Event event = eventRepository.findOne(eventId);
        if (event == null)
            return newErrorResult(MessageConstant.EVENT_NOT_FOUND);
        if (getAccessLevel(event, userCredentials.getUserId()).LEVEL < AccessLevel.DELETE.LEVEL)
            return newErrorResult(MessageConstant.ACCESS_DENIED);
        eventRepository.delete(eventId);
        return newOkResult(MessageConstant.EVENT_DELETED);
    }

    @RequestMapping(value="/{eventId}/attend")
    public ResultResponse attendToEvent(@PathVariable String eventId, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication)principal).getPrincipal();
        Event event = eventRepository.findOne(eventId);
        if (event == null)
            return newErrorResult(MessageConstant.EVENT_NOT_FOUND);
        return newOkResult(MessageConstant.EVENT_UPDATED, eventRepository.attendEvent(event.getId(), userCredentials.getUserId()));
    }

    private AccessLevel getAccessLevel(Event event, String userId) {
        return userId.equals(event.getOwnerId()) ? AccessLevel.ALL : AccessLevel.READ;
    }
}
