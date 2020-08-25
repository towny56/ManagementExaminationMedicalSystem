package com.fixit.areas.appointments.services;

import com.fixit.areas.appointments.entities.Appointment;
import com.fixit.areas.appointments.models.service.AppointmentServiceModel;
import com.fixit.areas.ward.entities.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface AppointmentService {
    AppointmentServiceModel create(AppointmentServiceModel appointmentServiceModel);

    Set<AppointmentServiceModel> findAllByDateAndWard(String date, Ward ward);
    Page<AppointmentServiceModel> findAllAppointments(Authentication authentication, Pageable pageable);
    //Set<AppointmentServiceModel> findAllByUser(String username);
}
