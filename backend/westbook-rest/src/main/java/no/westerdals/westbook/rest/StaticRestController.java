package no.westerdals.westbook.rest;

import no.westerdals.westbook.model.StudyField;
import no.westerdals.westbook.model.Tag;
import no.westerdals.westbook.mongodb.StudyFieldRepository;
import no.westerdals.westbook.mongodb.TagRepository;
import no.westerdals.westbook.responses.ResolvedTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/static")
public class StaticRestController
{
    @Autowired
    private StudyFieldRepository studyFieldRepository;

    @Autowired
    private TagRepository tagRepository;

    @RequestMapping(value="/studyfield", method=RequestMethod.GET)
    public List<StudyField> getAllStudyFields() {
        return studyFieldRepository.findAll();
    }

    @RequestMapping(value="/studyfield/{studyFieldId}", method=RequestMethod.GET)
    public StudyField getStudyFieldById(@PathVariable String studyFieldId) {
        return studyFieldRepository.findOne(studyFieldId);
    }

    @RequestMapping(value="/studyfield/by-name/{studyFieldName}", method=RequestMethod.GET)
    public StudyField getStudyFieldByName(@PathVariable String studyFieldName) {
        return studyFieldRepository.getByName(studyFieldName);
    }

    @RequestMapping(value="/studyfield", method=RequestMethod.POST)
    public StudyField createStudyField(@RequestBody StudyField studyField) {
        studyField.setId(null);
        return studyFieldRepository.insert(studyField);
    }

    @RequestMapping(value="/tags")
    public List<ResolvedTag> getAllTags() {
        return null;
    }
}
