package com.fixit.areas.role.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fixit.areas.role.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByAuthority(String authority);
}
