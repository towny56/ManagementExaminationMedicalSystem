package com.fixit.areas.log.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fixit.areas.log.entities.Log;

@Repository
public interface LoggerRepository extends JpaRepository<Log, String> {
}
