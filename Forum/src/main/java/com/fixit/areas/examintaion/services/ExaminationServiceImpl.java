package com.fixit.areas.examintaion.services;

import com.fixit.areas.examintaion.enitities.Examination;
import com.fixit.areas.examintaion.models.service.ExaminationServiceModel;
import com.fixit.areas.examintaion.repositories.ExaminationRepository;
import com.fixit.areas.result.entities.Result;
import com.fixit.areas.result.entities.ResultBlood;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.users.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminationServiceImpl implements ExaminationService{

    @Value("${examination.status.open}")
    private String statusOpen;

    @Value("${examination.status.processed}")
    private String statusProcessed;

    @Value("pending")
    private String statusPending;

    @Value("closed")
    private String statusClosed;

    private final ExaminationRepository examinationRepository;
    private final UsersService usersService;

    private final ModelMapper modelMapper;

    @Autowired
    public ExaminationServiceImpl(ExaminationRepository examinationRepository, @Lazy UsersService usersService, ModelMapper modelMapper){
        this.examinationRepository = examinationRepository;
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(ExaminationServiceModel examinationServiceModel) {
        Examination examination = this.modelMapper.map(examinationServiceModel, Examination.class);
        this.examinationRepository.save(examination);
    }

    @Override
    public ExaminationServiceModel findById(Long id) {

        Examination examination = this.examinationRepository.findById(id).orElse(null);
        ExaminationServiceModel examinationServiceModel = this.modelMapper.map(examination, ExaminationServiceModel.class);
        return examinationServiceModel;
    }

    @Override
    public Set<ExaminationServiceModel> findAll() {

        Set<ExaminationServiceModel> examinationServiceModels = new HashSet<>();

        this.examinationRepository.findAll().forEach(examination -> {
            ExaminationServiceModel examinationServiceModel = this.modelMapper.map(examination, ExaminationServiceModel.class);
            examinationServiceModels.add(examinationServiceModel);
        });

        return examinationServiceModels;
    }

    @Override
    public Page<ExaminationServiceModel> findAllOpen(Authentication authentication, Pageable pageable) {

        Users user = (Users) authentication.getPrincipal();
        Page<Examination> examinations;
        //Page<Examination> examinations = this.examinationRepository.findAllByStatus(statusOpen, pageable);

        if (this.usersService.hasDoctorRights(authentication)){
            examinations = this.examinationRepository.findAllByStatusAndWard(statusOpen, user.getWard(), pageable);
        } else if (this.usersService.hasPatientRights(authentication)) {
            examinations = this.examinationRepository.findAllByStatusAndPatient(statusOpen, user, pageable);
        } else {
            examinations = this.examinationRepository.findAllByStatus(statusOpen, pageable);
        }

        List<ExaminationServiceModel> examinationServiceModelsList = new ArrayList<>();

        examinations.forEach(examination -> {
            ExaminationServiceModel examinationServiceModel = this.modelMapper.map(examination, ExaminationServiceModel.class);
            examinationServiceModelsList.add(examinationServiceModel);
        });

        Page<ExaminationServiceModel> examinationServiceModels = new PageImpl<ExaminationServiceModel>(examinationServiceModelsList, pageable, examinations.getTotalElements());
        return examinationServiceModels;
    }

    @Override
    public void changeOpen(Long id) {
        Examination examination = this.examinationRepository.findById(id).orElse(null);
        examination.setStatus(this.statusProcessed);
        this.examinationRepository.save(examination);
    }

    @Override
    public Page<ExaminationServiceModel> findAllProcessed(Authentication authentication, Pageable pageable) {

        Users user = (Users) authentication.getPrincipal();
        Page<Examination> examinations;
        //Page<Examination> examinations = this.examinationRepository.findAllByStatus(statusProcessed, pageable);

        if (this.usersService.hasDoctorRights(authentication)){
            examinations = this.examinationRepository.findAllByStatusAndWard(statusProcessed, user.getWard(), pageable);
        } else if (this.usersService.hasPatientRights(authentication)) {
            examinations = this.examinationRepository.findAllByStatusAndPatient(statusProcessed, user, pageable);
        } else {
            examinations = this.examinationRepository.findAllByStatus(statusProcessed, pageable);
        }

        List<ExaminationServiceModel> examinationServiceModelsList = new ArrayList<>();

        examinations.forEach(examination -> {
            ExaminationServiceModel examinationServiceModel = this.modelMapper.map(examination, ExaminationServiceModel.class);
            examinationServiceModelsList.add(examinationServiceModel);
        });

        Page<ExaminationServiceModel> examinationServiceModels = new PageImpl<ExaminationServiceModel>(examinationServiceModelsList, pageable, examinations.getTotalElements());
        return examinationServiceModels;
    }

    @Override
    public void changeProcessed(Long id) {
        Examination examination = this.examinationRepository.findById(id).orElse(null);
        examination.setStatus(this.statusPending);
        this.examinationRepository.save(examination);
    }

    @Override
    public Page<ExaminationServiceModel> findAllPending(Authentication authentication, Pageable pageable) {

        Users user = (Users) authentication.getPrincipal();
        Page<Examination> examinations;
        //Page<Examination> examinations = this.examinationRepository.findAllByStatus(statusPending, pageable);

        if (this.usersService.hasDoctorRights(authentication)){
            examinations = this.examinationRepository.findAllByStatusAndWard(statusPending, user.getWard(), pageable);
        } else if (this.usersService.hasPatientRights(authentication)) {
            examinations = this.examinationRepository.findAllByStatusAndPatient(statusPending, user, pageable);
        } else {
            examinations = this.examinationRepository.findAllByStatus(statusPending, pageable);
        }

        List<ExaminationServiceModel> examinationServiceModelsList = new ArrayList<>();

        examinations.forEach(examination -> {
            ExaminationServiceModel examinationServiceModel = this.modelMapper.map(examination, ExaminationServiceModel.class);
            examinationServiceModelsList.add(examinationServiceModel);
        });

        Page<ExaminationServiceModel> examinationServiceModels = new PageImpl<ExaminationServiceModel>(examinationServiceModelsList, pageable, examinations.getTotalElements());
        return examinationServiceModels;
    }

    @Override
    public void changePending(Long id) {
        // TODO:
        // NEED TO SAVE THE RESULT
        Examination examination = this.examinationRepository.findById(id).orElse(null);
        examination.setStatus(this.statusClosed);
        this.examinationRepository.save(examination);
    }

    @Override
    public Page<ExaminationServiceModel> findAllClosed(Authentication authentication, Pageable pageable) {

        Users user = (Users) authentication.getPrincipal();
        Page<Examination> examinations;
        //Page<Examination> examinations = this.examinationRepository.findAllByStatus(statusClosed, pageable);

        if (this.usersService.hasDoctorRights(authentication)){
            examinations = this.examinationRepository.findAllByStatusAndWard(statusClosed, user.getWard(), pageable);
        } else if (this.usersService.hasPatientRights(authentication)) {
            examinations = this.examinationRepository.findAllByStatusAndPatient(statusClosed, user, pageable);
        } else {
            examinations = this.examinationRepository.findAllByStatus(statusClosed, pageable);
        }

        List<ExaminationServiceModel> examinationServiceModelsList = new ArrayList<>();

        examinations.forEach(examination -> {
            ExaminationServiceModel examinationServiceModel = this.modelMapper.map(examination, ExaminationServiceModel.class);
            examinationServiceModelsList.add(examinationServiceModel);
        });

        Page<ExaminationServiceModel> examinationServiceModels = new PageImpl<ExaminationServiceModel>(examinationServiceModelsList, pageable, examinations.getTotalElements());
        return examinationServiceModels;
    }

    @Override
    public void viewClosed(Long id) {
        // TODO:
        // NEED TO SEE THE INFORMATION OF THE RESULT WITH THE WANTED ID
        Examination examination = this.examinationRepository.findById(id).orElse(null);
    }

    @Override
    public void setResult(Long id, Result result) {

        Examination examination = this.examinationRepository.findById(id).orElse(null);
        examination.setResult(result);
        this.examinationRepository.save(examination);
    }
}
