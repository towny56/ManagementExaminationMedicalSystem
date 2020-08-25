package com.fixit.areas.ward.models.view;

import java.util.List;
import java.util.Set;

public class WardFreeAppointmentsViewModel {
    private Long id;
    private String wardName;
    private String roomNumber;

    private String date;
    private List<String> appointments;

    // TODO:
    // can add these sets once the view models are done
    // if we are not gonna use them we can get rid of the sets
    /*
    private Set<UserViewModel> doctors;
    private Set<ExaminationViewModel> examinations;
    private Set<ResultViewModel> results;
    */

    public WardFreeAppointmentsViewModel() {
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
