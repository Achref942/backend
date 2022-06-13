package com.example.pointini.entities;

import com.example.pointini.entities.Enum.ConfinoConfir;
import com.example.pointini.entities.Enum.SensOperation;
import com.example.pointini.entities.Enum.TypeOperation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private double montant;
    private TypeOperation typeOperation;
    private SensOperation sensOperation;
    private Date dateoperation;
    private LocalDateTime datecreation=LocalDateTime.now();
    private ConfinoConfir etat;



    @ManyToOne
    private FichePaie fichePaie;

    @ManyToOne
    private User user;

}
