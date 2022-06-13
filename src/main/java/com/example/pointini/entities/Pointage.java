package com.example.pointini.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pointage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime arrive;
    private Date dateArrive;
    private LocalTime sortir;
    private Date dateSortir;
    private Date date;
    private int etat;

    @ManyToOne
    private User user;

@JsonIgnore
    @ManyToOne
    private FichePaie fichePaie;

}
