package com.fixit.areas.examintaion.repositories;

import com.fixit.areas.examintaion.enitities.Examination;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.ward.entities.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    Page<Examination> findAllByStatus(String status, Pageable pageable);
    Page<Examination> findAllByPatient(Users patient, Pageable pageable);
    Page<Examination> findAllByWard(Ward ward, Pageable pageable);

    Page<Examination> findAllByStatusAndPatient(String status, Users patient, Pageable pageable);
    Page<Examination> findAllByStatusAndWard(String status, Ward ward, Pageable pageable);
}
