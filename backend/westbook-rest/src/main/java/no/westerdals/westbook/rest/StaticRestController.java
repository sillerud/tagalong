package no.westerdals.westbook.rest;

import no.westerdals.westbook.model.StudyField;
import no.westerdals.westbook.mongodb.StudyFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StaticRestController
{
    @Autowired
    private StudyFieldRepository studyFieldRepository;

    @RequestMapping(value="/rest/v1/static/studyfield/{studyFieldId}", method=RequestMethod.GET)
    public StudyField getStudyFieldById(@PathVariable String studyFieldId)
    {
        return studyFieldRepository.findOne(studyFieldId);
    }

    @RequestMapping(value="/rest/v1/static/studyfield/by-name/{studyFieldName}", method=RequestMethod.GET)
    public StudyField getStudyFieldByName(@PathVariable String studyFieldName)
    {
        return studyFieldRepository.getByName(studyFieldName);
    }

    @RequestMapping(value="/rest/v1/static/studyfield", method=RequestMethod.POST)
    public StudyField createStudyField(@RequestBody StudyField studyField)
    {
        studyField.setId(null);
        return studyFieldRepository.insert(studyField);
    }
}
