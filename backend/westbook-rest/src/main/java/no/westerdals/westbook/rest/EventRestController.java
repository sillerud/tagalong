package no.westerdals.westbook.rest;

import no.westerdals.westbook.mongodb.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1/events")
public class EventRestController {
    @Autowired
    private EventRepository eventRepository;
}
