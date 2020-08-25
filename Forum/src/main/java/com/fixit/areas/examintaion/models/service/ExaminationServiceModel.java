package com.fixit.areas.examintaion.models.service;

import com.fixit.areas.appointments.models.service.AppointmentServiceModel;
import com.fixit.areas.users.models.service.UsersServiceModel;
import com.fixit.areas.ward.models.service.WardServiceModel;

public class ExaminationServiceModel {

    private Long id;
    private String status;
    private WardServiceModel ward;
    private UsersServiceModel patient;

    // probably this field is redundant
    private AppointmentServiceModel appointment;

    //TODO:
    //add this field once service model for result is ready
    //private ResultServiceModel result;

    private String date;
    private String time;

    public ExaminationServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public AppointmentServiceModel getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentServiceModel appointment) {
        this.appointment = appointment;
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
}
