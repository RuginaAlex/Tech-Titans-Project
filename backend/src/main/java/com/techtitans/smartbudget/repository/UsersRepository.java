package com.techtitans.smartbudget.repository;
import com.techtitans.smartbudget.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);

    @Query("SELECT u from Users u where u.username = :username")
    Optional<Users> findByUsername(String username);
}
