package com.fixit.areas.ward.models.view;

import com.fixit.areas.appointments.models.view.AppointmentViewModel;

import java.util.Set;

public class WardViewModel {

    private Long id;
    private String wardName;
    private String roomNumber;

    private Set<AppointmentViewModel> appointments;

    // TODO:
    // can add these sets once the view models are done
    // if we are not gonna use them we can get rid of the sets
    /*
    private Set<UserViewModel> doctors;
    private Set<ExaminationViewModel> examinations;
    private Set<ResultViewModel> results;
    */

    public WardViewModel() {
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

    public Set<AppointmentViewModel> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<AppointmentViewModel> appointments) {
        this.appointments = appointments;
    }
}
