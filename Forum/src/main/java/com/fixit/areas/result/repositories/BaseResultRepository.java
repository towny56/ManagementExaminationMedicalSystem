package com.fixit.areas.result.repositories;

import com.fixit.areas.result.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseResultRepository<T extends Result> extends JpaRepository<T, Long> {
       // @Query("select e from #{#entityName} e")
        //List<EntityType> findThemAll();
}

