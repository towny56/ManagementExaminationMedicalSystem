package com.fixit.areas.appointments.models.view;

import com.fixit.areas.users.models.view.UsersViewModel;
import com.fixit.areas.ward.models.view.WardViewModel;

import java.util.Date;

public class AppointmentViewModel {

    private Long id;
    private String date;
    private String time;

    private UsersViewModel patient;
    private WardViewModel ward;

    // TODO:
    // will add these fields once the view models are done
    /*
    private ExaminationViewModel examinations;
    */

    public AppointmentViewModel() {
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

    public UsersViewModel getPatient() {
        return patient;
    }

    public void setPatient(UsersViewModel patient) {
        this.patient = patient;
    }

    public WardViewModel getWard() {
        return ward;
    }

    public void setWard(WardViewModel ward) {
        this.ward = ward;
    }
}
