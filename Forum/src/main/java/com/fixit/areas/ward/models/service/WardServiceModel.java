package com.fixit.areas.ward.models.service;

import com.fixit.areas.appointments.models.service.AppointmentServiceModel;

import java.util.Set;

public class WardServiceModel {

    private Long id;
    private String wardName;
    private String roomNumber;

    private Set<AppointmentServiceModel> appointments;

    // TODO:
    // will add these sets once the service models are done
    /*
    private Set<UserServiceModel> doctors;
    private Set<ExaminationServiceModel> examinations;
    private Set<ResultServiceModel> results;
    */

    public WardServiceModel() {
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

    public Set<AppointmentServiceModel> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<AppointmentServiceModel> appointments) {
        this.appointments = appointments;
    }
}
