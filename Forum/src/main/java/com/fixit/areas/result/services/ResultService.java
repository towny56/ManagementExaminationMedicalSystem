package com.fixit.areas.result.services;


import com.fixit.areas.result.models.service.ResultServiceModel;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.ward.entities.Ward;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface ResultService {
    Set<ResultServiceModel> findAllResults(Authentication authentication);
    Set<ResultServiceModel> findAllByPatient(Users patient);
    Set<ResultServiceModel> findAllByWard(Ward ward);
    ResultServiceModel findById(Long id);

    long counterAllResults();
}
