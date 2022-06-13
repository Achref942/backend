package com.example.pointini.repository;

import com.example.pointini.entities.Conge;

import com.example.pointini.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;


public interface CongeRepository extends JpaRepository<Conge, Long> {

    public Conge getCongeByUserAndDatedebAndDatefin(User user, Date datedeb, Date datefin);

}
