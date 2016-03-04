package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom
{
    List<User> getByFirstname(String name);
    List<User> getByFirstname(String name, Pageable pageable);
    List<User> getBySurname(String surname);
    List<User> getBySurname(String surname, Pageable pageable);
    List<User> getByStudyFieldId(String id);
    List<User> findByFirstnameLikeIgnoreCase(String name, Pageable pageable);
    List<User> findBySurnameLikeIgnoreCase(String name, Pageable pageable);
    List<User> findByFirstnameLikeAndSurnameLikeIgnoreCase(String firstname, String surname);
    @Query("{firstname: ?0, surname: ?1}")
    User getByFullName(String firstname, String surname);
    User getByEmail(String email);
}
