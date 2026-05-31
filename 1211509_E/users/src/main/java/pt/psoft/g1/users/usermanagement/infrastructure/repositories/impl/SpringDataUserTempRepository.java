package pt.psoft.g1.users.usermanagement.infrastructure.repositories.impl;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.psoft.g1.users.usermanagement.model.User;
import pt.psoft.g1.users.usermanagement.model.UserTemp;
import pt.psoft.g1.users.usermanagement.repositories.UserTempRepository;

import java.util.Optional;

public interface SpringDataUserTempRepository
        extends UserTempRepository, CrudRepository<UserTemp, String> {

    @Override
    @Query("SELECT u FROM UserTemp u WHERE u.userId = :userId")
    Optional<UserTemp> findByUserId(@Param("userId") @NotNull String userId);

    @Override
    @Query("SELECT u FROM UserTemp u WHERE u.username = :username")
    Optional<UserTemp> findByUsername(@Param("username") @NotNull String username);

    @Override
    default void delete(String userId) {
        deleteById(userId);
    }
}
