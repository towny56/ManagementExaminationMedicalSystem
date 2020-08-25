package com.fixit.areas.result.models.binding;


import com.fixit.areas.users.entities.Users;
import com.fixit.areas.ward.entities.Ward;
import com.fixit.constants.Constants;

import javax.validation.constraints.Size;

public class ResultBindingModel {

    @Size(max = 1000, message = Constants.RESULT_DESCRIPTION_LENGTH)
    private String description;

    private String date;
    private String time;

    private String patient;
    private String ward;
    private String examination;

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

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }
}
