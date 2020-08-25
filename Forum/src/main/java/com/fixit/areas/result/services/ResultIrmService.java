package com.fixit.areas.result.services;

import com.fixit.areas.result.models.binding.ResultIrmBindingModel;
import com.fixit.areas.result.models.service.ResultIrmServiceModel;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.ward.entities.Ward;

import java.util.Set;

public interface ResultIrmService  {

    void createResultIrm(ResultIrmBindingModel resultIrmBindingModel);
    Set<ResultIrmServiceModel> findAllResultsIrm();
    Set<ResultIrmServiceModel> findAllByPatient(Users patient);
    Set<ResultIrmServiceModel> findAllByWard(Ward ward);
    ResultIrmServiceModel findById(Long id);

    long counterAllResultsIrm();
}
