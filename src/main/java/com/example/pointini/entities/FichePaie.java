package com.example.pointini.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FichePaie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;

    @OneToMany(mappedBy ="fichePaie")
    private List<User> users=new ArrayList<>();

    @OneToMany(mappedBy ="fichePaie")
    private List<Pointage> pointages=new ArrayList<>();

    @OneToMany(mappedBy ="fichePaie")
    private List<Operation> operations=new ArrayList<>();

}
