package com.example.pointini.entities;

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
public class JourFerie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private Date datedeb;
    private Date datefin;
    //private int nbJour;
    @ManyToMany(mappedBy = "jourFeries",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<User> users =new ArrayList<>();

}
