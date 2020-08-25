package com.fixit.areas.result.services;

import com.fixit.areas.examintaion.services.ExaminationService;
import com.fixit.areas.result.entities.ResultIrm;
import com.fixit.areas.result.models.binding.ResultIrmBindingModel;
import com.fixit.areas.result.models.service.ResultIrmServiceModel;
import com.fixit.areas.result.models.service.ResultServiceModel;
import com.fixit.areas.result.repositories.ResultIrmRepository;
import com.fixit.areas.result.repositories.ResultRepository;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.users.models.service.UsersServiceModel;
import com.fixit.areas.users.services.UsersService;
import com.fixit.areas.ward.entities.Ward;
import com.fixit.areas.ward.models.service.WardServiceModel;
import com.fixit.areas.ward.services.WardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

//@Primary
@Service
public class ResultIrmServiceImpl implements ResultIrmService {

    private final ResultRepository resultRepository;
    private final ResultIrmRepository resultIrmRepository;
    private final UsersService usersService;
    private final WardService wardService;
    private  final ExaminationService examinationService;
    private final ModelMapper modelMapper;

    @Autowired
    public ResultIrmServiceImpl(ResultRepository resultRepository, ResultIrmRepository resultIrmRepository, @Lazy UsersService usersService, WardService wardService, ExaminationService examinationService, ModelMapper modelMapper) {
        this.resultRepository = resultRepository;
        this.resultIrmRepository = resultIrmRepository;
        this.usersService = usersService;
        this.wardService = wardService;
        this.examinationService = examinationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createResultIrm(ResultIrmBindingModel resultIrmBindingModel) {

        UsersServiceModel usersServiceModel = this.usersService.findByUsername(resultIrmBindingModel.getPatient());
        WardServiceModel wardServiceModel = this.wardService.findByWardName(resultIrmBindingModel.getWard());
        ResultIrmServiceModel resultIrmServiceModel = this.modelMapper.map(resultIrmBindingModel, ResultIrmServiceModel.class);
        resultIrmServiceModel.setPatient(usersServiceModel);
        resultIrmServiceModel.setWard(wardServiceModel);

        ResultIrm resultIrm = this.modelMapper.map(resultIrmServiceModel, ResultIrm.class);
        ResultIrm resultIrmCreated = this.resultIrmRepository.save(resultIrm);

        this.examinationService.changePending(Long.parseLong(resultIrmBindingModel.getExamination()));
        this.examinationService.setResult(Long.parseLong(resultIrmBindingModel.getExamination()), resultIrmCreated);
    }

    @Override
    public Set<ResultIrmServiceModel> findAllResultsIrm() {

        Set<ResultIrmServiceModel> resultIrmServiceModels = new HashSet<>();

        this.resultIrmRepository.findAll().forEach(resultIrm -> {
            ResultIrmServiceModel resultIrmServiceModel = this.modelMapper.map(resultIrm,ResultIrmServiceModel.class);
            resultIrmServiceModels.add(resultIrmServiceModel);
        });

        return resultIrmServiceModels;
    }

    @Override
    public Set<ResultIrmServiceModel> findAllByPatient(Users patient) {

        Set<ResultIrmServiceModel> resultIrmServiceModels = new HashSet<>();

        this.resultIrmRepository.findAllByPatient(patient).forEach(resultIrm -> {
            ResultIrmServiceModel resultIrmServiceModel = this.modelMapper.map(resultIrm,ResultIrmServiceModel.class);
            resultIrmServiceModels.add(resultIrmServiceModel);
        });

        return resultIrmServiceModels;
    }

    @Override
    public Set<ResultIrmServiceModel> findAllByWard(Ward ward) {

        Set<ResultIrmServiceModel> resultIrmServiceModels = new HashSet<>();

        this.resultIrmRepository.findAllByWard(ward).forEach(resultIrm -> {
            ResultIrmServiceModel resultIrmServiceModel = this.modelMapper.map(resultIrm, ResultIrmServiceModel.class);
            resultIrmServiceModels.add(resultIrmServiceModel);
        });

        return resultIrmServiceModels;
    }

    @Override
    public ResultIrmServiceModel findById(Long id) {

        ResultIrm resultIrm = this.resultIrmRepository.findById(id).orElse(null);
        ResultIrmServiceModel resultIrmServiceModel = this.modelMapper.map(resultIrm, ResultIrmServiceModel.class);

        return resultIrmServiceModel;
    }

    @Override
    public long counterAllResultsIrm() {
        return this.resultIrmRepository.count();
    }
}
