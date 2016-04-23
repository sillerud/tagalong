package no.westerdals.tagalong.mongodb;

import no.westerdals.tagalong.model.User;

public interface UserRepositoryCustom
{
    User update(User user);
    void updateStudyField(String id, String studyFieldId);
}
