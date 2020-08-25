package com.fixit.areas.result.services;

import com.fixit.areas.result.entities.Result;
import com.fixit.areas.result.entities.ResultBlood;
import com.fixit.areas.result.entities.ResultIrm;
import com.fixit.areas.result.models.service.ResultBloodServiceModel;
import com.fixit.areas.result.models.service.ResultIrmServiceModel;
import com.fixit.areas.result.models.service.ResultServiceModel;
import com.fixit.areas.result.repositories.ResultRepository;
import com.fixit.areas.role.entities.Role;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.users.services.UsersService;
import com.fixit.areas.ward.entities.Ward;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final UsersService usersService;
    private final ModelMapper modelMapper;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository, UsersService usersService, ModelMapper modelMapper) {
        this.resultRepository = resultRepository;
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<ResultServiceModel> findAllResults(Authentication authentication) {

        Users user = (Users) authentication.getPrincipal();
        Set<Result> results;

        if (this.usersService.hasDoctorRights(authentication)){
            results = this.resultRepository.findAllByWard(user.getWard());
        } else if (this.usersService.hasPatientRights(authentication)) {
            results = this.resultRepository.findAllByPatient(user);
        } else {
            results = new HashSet<>(this.resultRepository.findAll());
        }

        Set<ResultServiceModel> resultServiceModels = new HashSet<>();

        results.forEach(result -> {
            ResultServiceModel resultServiceModel = this.modelMapper.map(result, ResultServiceModel.class);
            resultServiceModels.add(resultServiceModel);
        });

        return resultServiceModels;
    }

    @Override
    public Set<ResultServiceModel> findAllByPatient(Users patient) {

        Set<ResultServiceModel> resultServiceModels = new HashSet<>();

        this.resultRepository.findAllByPatient(patient).forEach(result -> {
            ResultServiceModel resultServiceModel = this.modelMapper.map(result, ResultServiceModel.class);
            resultServiceModels.add(resultServiceModel);
        });

        return resultServiceModels;
    }

    @Override
    public Set<ResultServiceModel> findAllByWard(Ward ward) {

        Set<ResultServiceModel> resultServiceModels = new HashSet<>();

        this.resultRepository.findAllByWard(ward).forEach(result -> {
            ResultServiceModel resultServiceModel = this.modelMapper.map(result, ResultServiceModel.class);
            resultServiceModels.add(resultServiceModel);
        });

        return resultServiceModels;
    }

    @Override
    public ResultServiceModel findById(Long id) {
        Result result = this.resultRepository.findById(id).orElse(null);
        return this.modelMapper.map(result, ResultServiceModel.class);
    }

    @Override
    public long counterAllResults() {
        return this.resultRepository.count();
    }
}