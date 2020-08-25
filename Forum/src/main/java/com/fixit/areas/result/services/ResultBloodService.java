package com.fixit.areas.result.services;

import com.fixit.areas.result.models.binding.ResultBloodBindingModel;
import com.fixit.areas.result.models.service.ResultBloodServiceModel;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.ward.entities.Ward;
import com.fixit.areas.ward.models.service.WardServiceModel;

import java.util.Set;

public interface ResultBloodService {

    void createResultBlood(ResultBloodBindingModel resultBloodBindingModel);
    Set<ResultBloodServiceModel> findAllResultsBlood();
    Set<ResultBloodServiceModel> findAllByPatient(Users patient);
    Set<ResultBloodServiceModel> findAllByWard(Ward ward);
    ResultBloodServiceModel findById(Long id);

    long counterAllResultsBlood();
}
