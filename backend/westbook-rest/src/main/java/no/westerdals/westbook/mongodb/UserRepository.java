package no.westerdals.westbook.mongodb;

import no.westerdals.westbook.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String>
{
    List<User> getByFirstname(String name);
    List<User> getBySurname(String surname);
    //User getByFullName(String firstname, String surname);
    User getByEmail(String email);
}
