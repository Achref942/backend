package com.example.pointini.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String adresse;
    private double salaire;
    private LocalTime heure_deb;
    private LocalTime heure_fin;
    private LocalDateTime date_creation=LocalDateTime.now();
    private Date date_fin_contrat;
    private String username;
    private String password;
    private int soldeconge;
    private int etat;
    private int presence;
    private boolean inOut;
    private double nbHeureTravUser;
    private Date date;
    private double nbHeureReatardvUser;
    private String photo;
    private String numTel;
    private Long pinCode;

    @ManyToOne
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Pointage> pointage;

    @ManyToOne
    private Entreprise entreprise;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Pause_User> pause_users = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Conge> conges;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_jourferie",
            joinColumns = {@JoinColumn(name = "User_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "JourFerie_id", referencedColumnName = "id")}
    )
    private List<JourFerie> jourFeries = new ArrayList<>();

    @ManyToOne
    private FichePaie fichePaie;



    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Operation> operation;



}
