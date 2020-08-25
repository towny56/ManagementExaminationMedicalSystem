package com.fixit.areas.result.repositories;

import com.fixit.areas.result.entities.ResultIrm;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.ward.entities.Ward;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
public interface ResultIrmRepository extends BaseResultRepository<ResultIrm> {
    Set<ResultIrm> findAllByPatient(Users user);
    Set<ResultIrm> findAllByWard(Ward ward);
}
