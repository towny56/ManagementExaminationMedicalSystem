package com.fixit.areas.users.models.view;

import com.fixit.areas.result.models.view.ResultBloodViewModel;
import com.fixit.areas.result.models.view.ResultIrmViewModel;
import com.fixit.areas.result.models.view.ResultViewModel;

import java.util.Set;

public class UserMedicalRecordViewModel {

    private String username;
    private String firstName;
    private String lastName;
    private String egn;
    private Set<ResultBloodViewModel> bloodResults;
    private Set<ResultIrmViewModel> irmResults;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public Set<ResultBloodViewModel> getBloodResults() {
        return bloodResults;
    }

    public void setBloodResults(Set<ResultBloodViewModel> bloodResults) {
        this.bloodResults = bloodResults;
    }

    public Set<ResultIrmViewModel> getIrmResults() {
        return irmResults;
    }

    public void setIrmResults(Set<ResultIrmViewModel> irmResults) {
        this.irmResults = irmResults;
    }
}
