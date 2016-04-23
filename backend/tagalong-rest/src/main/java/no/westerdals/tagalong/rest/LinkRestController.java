package no.westerdals.tagalong.rest;

import no.westerdals.tagalong.MessageConstant;
import no.westerdals.tagalong.model.Link;
import no.westerdals.tagalong.mongodb.LinkRepository;
import no.westerdals.tagalong.responses.ResultResponse;
import static no.westerdals.tagalong.responses.ResultResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/v1/links")
public class LinkRestController
{
    @Autowired
    private LinkRepository linkRepository;

    @RequestMapping(value="/{linkId}", method=RequestMethod.GET)
    public Link getLink(@PathVariable String linkId)
    {
        return linkRepository.findOne(linkId);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResultResponse createLink(@RequestBody Link link)
    {
        link.setId(null);
        Link result = linkRepository.insert(link);
        return newOkResult(MessageConstant.LINK_CREATED, result);
    }
}
