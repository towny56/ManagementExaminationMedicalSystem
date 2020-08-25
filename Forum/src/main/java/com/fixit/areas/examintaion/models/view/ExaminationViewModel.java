package com.fixit.areas.examintaion.models.view;

import com.fixit.areas.appointments.models.view.AppointmentViewModel;
import com.fixit.areas.users.models.view.UsersViewModel;
import com.fixit.areas.ward.models.view.WardViewModel;

public class ExaminationViewModel {
    private Long id;
    private String status;
    private WardViewModel ward;
    private UsersViewModel patient;

    //TODO:
    //add this field once view model for result is ready
    //private ResultViewModel result;

    private String date;
    private String time;

    public ExaminationViewModel() {
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
