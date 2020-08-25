package com.fixit.areas.result.models.service;


import com.fixit.areas.users.models.service.UsersServiceModel;
import com.fixit.areas.ward.models.service.WardServiceModel;

public class ResultServiceModel {

    private Long id;
    private String description;
    private String date;
    private String time;
    private WardServiceModel ward;
    private UsersServiceModel patient;

    //probably need to add this service modal
    //private ExaminationServiceModel examination;

    public ResultServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
