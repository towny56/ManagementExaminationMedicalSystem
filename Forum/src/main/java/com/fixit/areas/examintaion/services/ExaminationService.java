package com.fixit.areas.examintaion.services;

import com.fixit.areas.examintaion.models.service.ExaminationServiceModel;
import com.fixit.areas.result.entities.Result;
import com.fixit.areas.result.entities.ResultBlood;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface ExaminationService {
    void create(ExaminationServiceModel examinationServiceModel);

    ExaminationServiceModel findById(Long id);

    Set<ExaminationServiceModel> findAll();

    Page<ExaminationServiceModel> findAllOpen(Authentication authentication, Pageable pageable);
    Page<ExaminationServiceModel> findAllProcessed(Authentication authentication, Pageable pageable);
    Page<ExaminationServiceModel> findAllPending(Authentication authentication, Pageable pageable);
    Page<ExaminationServiceModel> findAllClosed(Authentication authentication, Pageable pageable);

    void changeOpen(Long id);
    void changeProcessed(Long id);
    void changePending(Long id);

    void viewClosed(Long id);
    void setResult(Long id, Result result);
}
