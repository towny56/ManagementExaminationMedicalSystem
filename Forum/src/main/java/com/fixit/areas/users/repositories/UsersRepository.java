package com.fixit.areas.users.repositories;

import com.fixit.areas.users.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findOneByUsername(String username);

    Users findByEmail(String email);
}
