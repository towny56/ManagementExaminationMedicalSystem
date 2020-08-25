package com.fixit.areas.result.services;

import com.fixit.areas.examintaion.enitities.Examination;
import com.fixit.areas.examintaion.services.ExaminationService;
import com.fixit.areas.result.entities.ResultBlood;
import com.fixit.areas.result.models.binding.ResultBloodBindingModel;
import com.fixit.areas.result.models.service.ResultBloodServiceModel;
import com.fixit.areas.result.repositories.ResultBloodRepository;
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
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

//@Primary
@Service
public class ResultBloodServiceImpl implements ResultBloodService {

    private final ResultRepository resultRepository;
    private final ResultBloodRepository resultBloodRepository;
    private final UsersService usersService;
    private final WardService wardService;
    private  final ExaminationService examinationService;
    private final ModelMapper modelMapper;

    @Autowired
    public ResultBloodServiceImpl(ResultRepository resultRepository, ResultBloodRepository resultBloodRepository, @Lazy UsersService usersService, WardService wardService, ExaminationService examinationService, ModelMapper modelMapper) {
        this.resultRepository=resultRepository;
        this.resultBloodRepository = resultBloodRepository;
        this.usersService = usersService;
        this.wardService = wardService;
        this.examinationService = examinationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createResultBlood(ResultBloodBindingModel resultBloodBindingModel) {

        UsersServiceModel usersServiceModel = this.usersService.findByUsername(resultBloodBindingModel.getPatient());
        WardServiceModel wardServiceModel = this.wardService.findByWardName(resultBloodBindingModel.getWard());
        ResultBloodServiceModel resultBloodServiceModel = this.modelMapper.map(resultBloodBindingModel, ResultBloodServiceModel.class);
        resultBloodServiceModel.setPatient(usersServiceModel);
        resultBloodServiceModel.setWard(wardServiceModel);

        ResultBlood resultBlood = this.modelMapper.map(resultBloodServiceModel, ResultBlood.class);
        ResultBlood resultBloodCreated = this.resultBloodRepository.save(resultBlood);

        this.examinationService.changePending(Long.parseLong(resultBloodBindingModel.getExamination()));
        this.examinationService.setResult(Long.parseLong(resultBloodBindingModel.getExamination()), resultBloodCreated);
    }

    @Override
    public Set<ResultBloodServiceModel> findAllResultsBlood() {

        Set<ResultBloodServiceModel> resultBloodServiceModels = new HashSet<>();

        this.resultBloodRepository.findAll().forEach(resultBlood -> {
            ResultBloodServiceModel resultBloodServiceModel = this.modelMapper.map(resultBlood, ResultBloodServiceModel.class);
            resultBloodServiceModels.add(resultBloodServiceModel);
        });

        return resultBloodServiceModels;
    }

    @Override
    public Set<ResultBloodServiceModel> findAllByPatient(Users patient) {

        Set<ResultBloodServiceModel> resultBloodServiceModels = new HashSet<>();

        this.resultBloodRepository.findAllByPatient(patient).forEach(resultBlood -> {
            ResultBloodServiceModel resultBloodServiceModel = this.modelMapper.map(resultBlood, ResultBloodServiceModel.class);
            resultBloodServiceModels.add(resultBloodServiceModel);
        });

        return resultBloodServiceModels;
    }

    @Override
    public Set<ResultBloodServiceModel> findAllByWard(Ward ward) {

        Set<ResultBloodServiceModel> resultBloodServiceModels = new HashSet<>();

        this.resultBloodRepository.findAllByWard(ward).forEach(resultBlood -> {
            ResultBloodServiceModel resultBloodServiceModel = this.modelMapper.map(resultBlood, ResultBloodServiceModel.class);
            resultBloodServiceModels.add(resultBloodServiceModel);
        });

        return resultBloodServiceModels;
    }

    @Override
    public ResultBloodServiceModel findById(Long id) {

        ResultBlood resultBlood = this.resultBloodRepository.findById(id).orElse(null);
        ResultBloodServiceModel resultBloodServiceModel = this.modelMapper.map(resultBlood, ResultBloodServiceModel.class);
        return resultBloodServiceModel;
    }

    @Override
    public long counterAllResultsBlood() {
        return this.resultBloodRepository.count();
    }
}
