package com.fixit.areas.ward.repository;

import com.fixit.areas.ward.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {

    Ward findByWardName(String wardName);

    @Query(value = "SELECT ward_name FROM ward", nativeQuery = true)
    Set<String> findAllWardNames();

    //Ward findAllByDate(String date);
}
