package com.fixit.areas.users.models.service;

import com.fixit.areas.role.models.service.RoleServiceModel;
import com.fixit.areas.ward.models.service.WardServiceModel;

import java.util.Set;

public class UsersServiceModel {

    private Long id;

    private String password;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String egn;

    private WardServiceModel ward;

    private Set<RoleServiceModel> roles;

//    private Set<AppointmentServiceModel> appointments;
//
//    private Set<ExaminationServiceModel> examinations;
//
//    private Set<ResultServiceModel> results;

    private String roleName;

    private String wardName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public WardServiceModel getWard() {
        return ward;
    }

    public void setWard(WardServiceModel ward) {
        this.ward = ward;
    }

    public Set<RoleServiceModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleServiceModel> roles) {
        this.roles = roles;
    }

//    public Set<AppointmentServiceModel> getAppointments() {
//        return appointments;
//    }
//
//    public void setAppointments(Set<AppointmentServiceModel> appointments) {
//        this.appointments = appointments;
//    }
//
//    public Set<ExaminationServiceModel> getExaminations() {
//        return examinations;
//    }
//
//    public void setExaminations(Set<ExaminationServiceModel> examinations) {
//        this.examinations = examinations;
//    }
//
//    public Set<ResultServiceModel> getResults() {
//        return results;
//    }
//
//    public void setResults(Set<ResultServiceModel> results) {
//        this.results = results;
//    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
}
