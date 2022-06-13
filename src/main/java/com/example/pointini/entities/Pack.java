package com.example.pointini.entities;

import com.example.pointini.entities.Enum.DaysMonths;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.print.attribute.DateTimeSyntax;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pack implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private double frais;
    private DaysMonths type;
    private int duree;
    private Date expiration;

@ManyToMany(mappedBy = "pack",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Entreprise> entreprises =new ArrayList<>();


}
