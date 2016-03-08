package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.User;

public interface UserRepositoryCustom
{
    User update(User user);
    void updateStudyField(String id, String studyFieldId);
}
