package com.fixit.areas.appointments.services;

import com.fixit.areas.appointments.entities.Appointment;
import com.fixit.areas.appointments.models.service.AppointmentServiceModel;
import com.fixit.areas.appointments.repositories.AppointmentRepository;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.users.repositories.UsersRepository;
import com.fixit.areas.users.services.UsersService;
import com.fixit.areas.ward.entities.Ward;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;
    private final UsersService usersService;
    private final ModelMapper modelMapper;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, @Lazy UsersService usersService, ModelMapper modelMapper) {

        this.appointmentRepository = appointmentRepository;
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @Override
    public AppointmentServiceModel create(AppointmentServiceModel appointmentServiceModel) {
        Appointment appointment = this.modelMapper.map(appointmentServiceModel, Appointment.class);
        return this.modelMapper.map(this.appointmentRepository.save(appointment), AppointmentServiceModel.class);
    }

    @Override
    public Set<AppointmentServiceModel> findAllByDateAndWard(String date, Ward ward) {

        Set<AppointmentServiceModel> appointmentServiceModels = new HashSet<>();
        this.appointmentRepository.findAllByDateAndWard(date, ward).forEach(appointment -> {
            AppointmentServiceModel appointmentServiceModel = this.modelMapper.map(appointment, AppointmentServiceModel.class);
            appointmentServiceModels.add(appointmentServiceModel);
        });

        return appointmentServiceModels;
    }

    @Override
    public Page<AppointmentServiceModel> findAllAppointments(Authentication authentication, Pageable pageable) {

        Users user = (Users) authentication.getPrincipal();
        Page<Appointment> appointments;
        //Page<Appointment> appointments = this.appointmentRepository.findAll(pageable);

        if (this.usersService.hasDoctorRights(authentication)){
            appointments = this.appointmentRepository.findAllByWard(user.getWard(), pageable);
        } else if (this.usersService.hasPatientRights(authentication)) {
            appointments = this.appointmentRepository.findAllByPatient(user, pageable);
        } else {
            appointments = this.appointmentRepository.findAll(pageable);
        }

        List<AppointmentServiceModel> appointmentServiceModelsList = new ArrayList<>();

        appointments.forEach(appointment -> {
            AppointmentServiceModel appointmentServiceModel = this.modelMapper.map(appointment, AppointmentServiceModel.class);
            appointmentServiceModelsList.add(appointmentServiceModel);
        });

        Page<AppointmentServiceModel> appointmentServiceModels = new PageImpl<>(appointmentServiceModelsList, pageable, appointments.getTotalElements());

        return appointmentServiceModels;
    }
}
