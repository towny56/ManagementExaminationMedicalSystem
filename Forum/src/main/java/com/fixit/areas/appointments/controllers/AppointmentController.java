package com.fixit.areas.appointments.controllers;

import com.fixit.abstractions.controller.BaseController;
import com.fixit.areas.appointments.models.service.AppointmentServiceModel;
import com.fixit.areas.appointments.models.view.AppointmentViewModel;
import com.fixit.areas.appointments.services.AppointmentService;
import com.fixit.areas.role.entities.Role;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.users.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/appointments")
public class AppointmentController extends BaseController {

    private final AppointmentService appointmentService;
    private final UsersService usersService;
    private final ModelMapper modelMapper;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, UsersService usersService, ModelMapper modelMapper){
        this.appointmentService = appointmentService;
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public ModelAndView allAppointments(@PageableDefault(size = 10) Pageable pageable, Authentication authentication){

        Page<AppointmentServiceModel> appointmentServiceModels = this.appointmentService.findAllAppointments(authentication, pageable);

        List<AppointmentViewModel> appointmentViewModelList = new ArrayList<>();

        appointmentServiceModels.forEach(appointmentServiceModel -> {
            AppointmentViewModel appointmentViewModel = this.modelMapper.map(appointmentServiceModel, AppointmentViewModel.class);
            appointmentViewModelList.add(appointmentViewModel);
        });

        Page<AppointmentViewModel> appointmentViewModelPage = new PageImpl<>(appointmentViewModelList, pageable, appointmentServiceModels.getTotalElements());
        return super.view("views/appointments/all", appointmentViewModelPage);
    }
}
