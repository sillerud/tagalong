package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String>
{
    List<User> getByFirstname(String name);
    List<User> getByFirstname(String name, Pageable pageable);
    List<User> getBySurname(String surname);
    List<User> getBySurname(String surname, Pageable pageable);
    List<User> getByStudyFieldId(String id);
    @Query("{firstname: ?0, surname: ?1}")
    User getByFullName(String firstname, String surname);
    User getByEmail(String email);
}
