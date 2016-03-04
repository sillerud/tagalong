package no.westerdals.westbook.rest;

import no.westerdals.westbook.MessageConstant;
import no.westerdals.westbook.model.Page;
import no.westerdals.westbook.mongodb.PageRepository;
import no.westerdals.westbook.responses.ResultResponse;
import static no.westerdals.westbook.responses.ResultResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/pages")
public class PageRestController
{
    @Autowired
    private PageRepository pageRepository;

    @RequestMapping(method=RequestMethod.GET)
    public List<Page> getAllPages(@RequestParam(name="maxResults",defaultValue="10") int maxResults) {
        return pageRepository.findAll(new PageRequest(0, maxResults)).getContent();
    }

    @RequestMapping(value="/{pageId}", method=RequestMethod.GET)
    public Page getPageById(@PathVariable String pageId)
    {
        Page result = pageRepository.findByCustomUrl(pageId);
        if (result == null)
            result = pageRepository.findOne(pageId);
        return result;
    }

    @RequestMapping(value="/by-name/{name}", method=RequestMethod.GET)
    public List<Page> getPagesByName(@PathVariable String name)
    {
        return pageRepository.findByName(name, new PageRequest(0, 20));
    }

    @RequestMapping(value="/by-user/{userId}", method=RequestMethod.GET)
    public List<Page> getPagesByOwner(@PathVariable String userId)
    {
        return pageRepository.getByUserId(userId);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResultResponse createNewPage(@RequestBody Page page)
    {
        page.setId(null);
        page.setUserId(null); //TODO
        Page result = pageRepository.insert(page);
        return newOkResult(MessageConstant.PAGE_CREATED, result);
    }
}
