package pt.psoft.g1.users.usermanagement.repositories;

import pt.psoft.g1.users.usermanagement.model.User;
import pt.psoft.g1.users.usermanagement.model.UserTemp;

import java.util.Optional;

public interface UserTempRepository {
    UserTemp save(UserTemp user);
    Optional<UserTemp> findByUserId(String userId);
    Optional<UserTemp> findByUsername(String username);
    void delete(String userId);
}
