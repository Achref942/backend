package com.example.pointini.repository;


import com.example.pointini.entities.Pointage;
import com.example.pointini.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.List;


public interface PointgeRepository extends JpaRepository<Pointage, Long> {

     Pointage findPointageByUserIdAndEtat(Long user,int etat);
     Pointage findPointageByUserIdAndEtatAndDate(Long idUser, int etat, Date date);
     Pointage findPointageByDateAndUserId(Date date,Long idUser);
     List<Pointage> findPointageByUserId(Long id);



}
