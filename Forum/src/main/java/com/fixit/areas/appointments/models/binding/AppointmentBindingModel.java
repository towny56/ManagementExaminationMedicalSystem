package com.fixit.areas.appointments.models.binding;

import java.util.Date;

public class AppointmentBindingModel {

    //TODO:
    // see if there are needed more field in this model
    // depends on the functionality from the FE
    private String date;
    private String time;

    public AppointmentBindingModel() {
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
