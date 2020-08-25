package com.fixit.areas.examintaion.enitities;

import com.fixit.areas.appointments.entities.Appointment;
import com.fixit.areas.result.entities.Result;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.ward.entities.Ward;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "examination")
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Users patient;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id", referencedColumnName = "id")
    private Result result;

    private String date;

    private String time;

    public Examination() {
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

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public Users getPatient() {
        return patient;
    }

    public void setPatient(Users patient) {
        this.patient = patient;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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
