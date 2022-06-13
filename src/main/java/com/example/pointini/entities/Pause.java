package com.example.pointini.entities;

import com.example.pointini.entities.Enum.HeureMiniute;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pause implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private HeureMiniute type;
    private String libelle;
    private int duree;
    private Date expirationPause;

    @JsonIgnore
    @OneToMany(mappedBy = "pause")
    private List<Pause_User> pause_users=new ArrayList<>();



}
