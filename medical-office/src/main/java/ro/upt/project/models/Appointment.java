package ro.upt.project.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String details;
    private LocalDateTime time;
    @ManyToOne
    private User doctor;
    @ManyToOne
    private User patient;

    public Appointment(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", details='" + details + '\'' +
                ", time=" + time +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }
}
