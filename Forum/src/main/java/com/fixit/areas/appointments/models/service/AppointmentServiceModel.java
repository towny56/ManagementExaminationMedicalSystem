package com.fixit.areas.appointments.models.service;

import com.fixit.areas.users.models.service.UsersServiceModel;
import com.fixit.areas.ward.models.service.WardServiceModel;

import java.util.Date;

public class AppointmentServiceModel {

    private Long id;
    private String date;
    private String time;

    private WardServiceModel ward;
    private UsersServiceModel patient;

    // TODO:
    // will add these fields once the service models are done
    /*
    private ExaminationServiceModel examinations;
    */

    public AppointmentServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public WardServiceModel getWard() {
        return ward;
    }

    public void setWard(WardServiceModel ward) {
        this.ward = ward;
    }

    public UsersServiceModel getPatient() {
        return patient;
    }

    public void setPatient(UsersServiceModel patient) {
        this.patient = patient;
    }
}
