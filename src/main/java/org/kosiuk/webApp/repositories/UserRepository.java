package org.kosiuk.webApp.repositories;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import org.kosiuk.webApp.entity.Role;
import org.kosiuk.webApp.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    @Transactional
    @Query("UPDATE User SET username = :username, email = :email WHERE id = :id")
    @Modifying
    void updateUser(@Param("id") Integer id, @Param("username") String username, @Param("email") String email);

    @Transactional
    @Query(value = "UPDATE role SET roles = :roles WHERE account_id = :account_id", nativeQuery = true)
    @Modifying
    void updateRoles(@Param("account_id") Integer account_id, @Param("roles") String roles);

    @Transactional
    @Query(value = "INSERT INTO role (account_id, roles) VALUES(:account_id, :roles) ", nativeQuery = true)
    @Modifying
    void createRoles(@Param("account_id") Integer account_id, @Param("roles") String roles);
}
