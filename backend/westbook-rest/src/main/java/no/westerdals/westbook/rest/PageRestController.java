package no.westerdals.westbook.rest;

import no.westerdals.westbook.MessageConstant;
import no.westerdals.westbook.ModelHelper;
import no.westerdals.westbook.model.AccessLevel;
import no.westerdals.westbook.model.Page;
import no.westerdals.westbook.model.UserCredentials;
import no.westerdals.westbook.mongodb.PageRepository;
import no.westerdals.westbook.responses.ResultResponse;
import static no.westerdals.westbook.responses.ResultResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @RequestMapping(value="/{pageId}", method=RequestMethod.PATCH)
    public ResultResponse updatePage(@PathVariable String pageId, @RequestBody Page updatedPage, Principal principal) {
        Page page = getPageById(pageId, principal);

        if (page == null)
            return newErrorResult(MessageConstant.PAGE_NOT_FOUND);

        if (page.getAccessLevel().LEVEL < AccessLevel.EDIT.LEVEL)
            return newErrorResult(MessageConstant.ACCESS_DENIED);

        if (updatedPage.getCustomUrl() != null && pageRepository.findByCustomUrl(updatedPage.getCustomUrl()) != null)
            return newErrorResult(MessageConstant.PAGE_URL_ALREADY_EXISTS_ERROR, "The page url " + updatedPage.getCustomUrl() + " is already in use.");

        return newOkResult(MessageConstant.PAGE_UPDATED, pageRepository.save(ModelHelper.mapObjects(page, updatedPage, Page.class)));
    }

    @RequestMapping(value="/{pageId}", method=RequestMethod.GET)
    public Page getPageById(@PathVariable String pageId, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication) principal).getPrincipal();
        Page result = pageRepository.findByCustomUrl(pageId);
        if (result == null)
            result = pageRepository.findOne(pageId);
        if (result != null)
            result.setAccessLevel(getAccessLevel(result, userCredentials.getUserId()));
        return result;
    }

    @RequestMapping(value="/{pageId}", method=RequestMethod.DELETE)
    public ResultResponse deletePage(@PathVariable String pageId, Principal principal) {
        UserCredentials userCredentials = (UserCredentials) ((Authentication) principal).getPrincipal();
        Page result = pageRepository.findOne(pageId);
        if (result == null)
            return newErrorResult(MessageConstant.PAGE_NOT_FOUND);
        if (getAccessLevel(result, userCredentials.getUserId()).LEVEL < AccessLevel.DELETE.LEVEL)
            return newErrorResult(MessageConstant.ACCESS_DENIED);
        pageRepository.delete(pageId);
        return newOkResult(MessageConstant.PAGE_DELETED);
    }

    @RequestMapping(value="/by-name/{name}", method=RequestMethod.GET)
    public List<Page> getPagesByName(@PathVariable String name) {
        return pageRepository.findByNameLikeIgnoreCase(name, new PageRequest(0, 20));
    }

    @RequestMapping(value="/by-user/{userId}", method=RequestMethod.GET)
    public List<Page> getPagesByOwner(@PathVariable String userId)
    {
        return pageRepository.getByUserId(userId);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResultResponse createNewPage(@RequestBody Page page) {
        page.setId(null);
        page.setUserId(null); //TODO
        Page result = pageRepository.insert(page);
        return newOkResult(MessageConstant.PAGE_CREATED, result);
    }

    private AccessLevel getAccessLevel(Page page, String userId) {
        if (userId.equals(page.getUserId()))
            return AccessLevel.ALL;
        if (page.getAdministrators() != null) {
            for (Page.PageAdministrator admin : page.getAdministrators()) {
                if (userId.equals(admin.getUserId()))
                    return admin.getAccessLevel();
            }
        }
        return AccessLevel.READ;
    }
}
