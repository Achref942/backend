package com.example.pointini.entities;

import com.example.pointini.entities.Enum.ConfinoConfir;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date datedeb;
    private Date datefin;
    private long nbConge;
    private int nbJour;
    private ConfinoConfir etat;
    @ManyToOne
    private User user;

}
