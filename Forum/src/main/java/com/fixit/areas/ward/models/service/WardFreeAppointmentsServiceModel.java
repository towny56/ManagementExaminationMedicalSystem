package com.fixit.areas.ward.models.service;

import java.util.List;
import java.util.Set;

public class WardFreeAppointmentsServiceModel {
    private Long id;
    private String wardName;
    private String roomNumber;

    private String date;
    private List<String> appointments;

    // TODO:
    // will add these sets once the service models are done
    /*
    private Set<UserServiceModel> doctors;
    private Set<ExaminationServiceModel> examinations;
    private Set<ResultServiceModel> results;
    */

    public WardFreeAppointmentsServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<String> appointments) {
        this.appointments = appointments;
    }
}
