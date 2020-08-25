package com.fixit.areas.result.models.view;

import com.fixit.areas.users.models.view.UsersViewModel;
import com.fixit.areas.ward.models.view.WardViewModel;

public class ResultViewModel {

    private Long id;
    private String description;
    private String date;
    private String time;
    private WardViewModel ward;
    private UsersViewModel patient;

    public ResultViewModel() {
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

    public WardViewModel getWard() {
        return ward;
    }

    public void setWard(WardViewModel ward) {
        this.ward = ward;
    }

    public UsersViewModel getPatient() {
        return patient;
    }

    public void setPatient(UsersViewModel patient) {
        this.patient = patient;
    }
}
