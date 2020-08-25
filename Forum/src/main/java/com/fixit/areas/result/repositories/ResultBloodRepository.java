package com.fixit.areas.result.repositories;

import com.fixit.areas.result.entities.ResultBlood;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.ward.entities.Ward;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
public interface ResultBloodRepository  extends BaseResultRepository<ResultBlood> {
    Set<ResultBlood> findAllByPatient(Users user);
    Set<ResultBlood> findAllByWard(Ward ward);
}

