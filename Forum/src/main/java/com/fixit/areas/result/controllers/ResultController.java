package com.fixit.areas.result.controllers;

import com.fixit.abstractions.controller.BaseController;
import com.fixit.areas.result.entities.ResultBlood;
import com.fixit.areas.result.models.binding.ResultBloodBindingModel;
import com.fixit.areas.result.models.binding.ResultIrmBindingModel;
import com.fixit.areas.result.models.service.ResultBloodServiceModel;
import com.fixit.areas.result.models.service.ResultIrmServiceModel;
import com.fixit.areas.result.models.service.ResultServiceModel;
import com.fixit.areas.result.models.view.ResultBloodViewModel;
import com.fixit.areas.result.models.view.ResultIrmViewModel;
import com.fixit.areas.result.models.view.ResultViewModel;
import com.fixit.areas.result.services.ResultBloodService;
import com.fixit.areas.result.services.ResultIrmService;
import com.fixit.areas.result.services.ResultService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;


@Controller
@RequestMapping("/results")
public class ResultController extends BaseController {

    private static final String UPLOADS_DIR = "tmp";

    @Value("${result.blood}")
    private String resultBlood;

    @Value("${result.irm}")
    private String resultIrm;

    public ResultIrmService resultIrmService;
    public ResultBloodService resultBloodService;
    public ResultService resultService;
    public ModelMapper modelMapper;

    @Autowired
    public ResultController( ResultService resultService,ResultIrmService resultIrmService,ResultBloodService resultBloodService, ModelMapper modelMapper) {
        this.resultIrmService = resultIrmService;
        this.resultBloodService = resultBloodService;
        this.resultService=resultService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public ModelAndView resultsAll(Authentication authentication){
        Set<ResultServiceModel> resultServiceModels = this.resultService.findAllResults(authentication);
        Set<ResultViewModel> resultViewModels = new HashSet<>();

        resultServiceModels.forEach(resultServiceModel -> {
            ResultViewModel resultViewModel = this.modelMapper.map(resultServiceModel, ResultViewModel.class);
            resultViewModels.add(resultViewModel);
        });

        return super.view("views/results/all", resultViewModels);
    }

    @PostMapping("/blood")
    public ModelAndView storeResultBlood(@Valid @ModelAttribute ResultBloodBindingModel resultBloodBindingModel, BindingResult bindingResult, Authentication authentication){

        this.resultBloodService.createResultBlood(resultBloodBindingModel);
        return super.redirect("/examinations/pending");
    }

    @PostMapping("/irm")
    public ModelAndView storeResultIrm(@Valid @ModelAttribute ResultIrmBindingModel resultIrmBindingModel, BindingResult bindingResult, @RequestParam("file") MultipartFile file, Authentication authentication){
        if (!file.isEmpty() && file.getOriginalFilename().length() > 0) {
            if (Pattern.matches("\\w+\\.(jpg|jpeg|bmp|png)", file.getOriginalFilename())) {
                handleMultipartFile(file);
                resultIrmBindingModel.setImg(file.getOriginalFilename());
            }
        }
        this.resultIrmService.createResultIrm(resultIrmBindingModel);
        return super.redirect("/examinations/pending");
    }

    @GetMapping("/{result}")
    public ModelAndView viewResult(@PathVariable Long result, Authentication authentication){

        ResultServiceModel resultServiceModel = this.resultService.findById(result);

        if(resultServiceModel.getWard().getWardName().equals(this.resultBlood)){

            ResultBloodServiceModel resultBloodServiceModel = this.resultBloodService.findById(result);
            ResultBloodViewModel resultBloodViewModel = this.modelMapper.map(resultBloodServiceModel, ResultBloodViewModel.class);
            return super.view("/views/results/blood-result", resultBloodViewModel);

        }else if(resultServiceModel.getWard().getWardName().equals(this.resultIrm)){

            ResultIrmServiceModel resultIrmServiceModel = this.resultIrmService.findById(result);
            ResultIrmViewModel resultIrmViewModel = this.modelMapper.map(resultIrmServiceModel, ResultIrmViewModel.class);
            return super.view("/views/results/irm-result", resultIrmViewModel);
        }
        else{
            return super.redirect("/results");
        }
    }

    private void handleMultipartFile(MultipartFile file) {
        try {
            File currentDir = new File(UPLOADS_DIR);
            if(!currentDir.exists()) {
                currentDir.mkdirs();
            }
            String path = currentDir.getAbsolutePath() + "/" + file.getOriginalFilename();
            path = new File(path).getAbsolutePath();
            File f = new File(path);
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(f));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
