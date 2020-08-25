package com.fixit.areas.log.controllers;

import com.fixit.abstractions.controller.BaseController;
import com.fixit.areas.log.models.service.LogServiceModel;
import com.fixit.areas.log.services.LoggerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/logs")
public class LoggerController extends BaseController {
    private final LoggerService loggerService;

    public LoggerController(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @GetMapping("/all")
    public ModelAndView all() {
        Set<LogServiceModel> allLogs = this.loggerService.findAllLogs();
        return super.view("views/logs/all", allLogs);
    }

    @PostMapping("/search")
    public ModelAndView search(@ModelAttribute LogServiceModel logModel) {
        Set<LogServiceModel> logsModels = this.loggerService.findAllLogs();

        if (logModel.getUser() == null || logModel.getUser().isEmpty()) {
            return super.view("views/logs/all", logsModels);
        }

        Set<LogServiceModel> logDtosByUser = logsModels.stream()
                .filter(log -> log.getUser().equals(logModel.getUser()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        return super.view("views/logs/all", logDtosByUser);
    }

    @PostMapping("/clear")
    public ModelAndView clear() {
        this.loggerService.deleteAll();
        return super.redirect("/");
    }
}
