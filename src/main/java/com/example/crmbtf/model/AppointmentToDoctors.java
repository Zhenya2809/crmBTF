package com.example.crmbtf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table(name = "t_doctorsappointments")
public class AppointmentToDoctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctorsappointments")
    private Long doctorsappointmentsID;
    @Column(name = "date")
    private Date date;
    @Column(name = "time")
    private Time time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorID")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientID")
    private Patient patient;


}
