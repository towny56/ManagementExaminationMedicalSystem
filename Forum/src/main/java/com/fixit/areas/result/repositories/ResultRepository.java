package com.fixit.areas.result.repositories;

import com.fixit.areas.result.entities.Result;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.ward.entities.Ward;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
public interface ResultRepository extends BaseResultRepository<Result> {
    Set<Result> findAllByPatient(Users user);
    Set<Result> findAllByWard(Ward ward);
}
