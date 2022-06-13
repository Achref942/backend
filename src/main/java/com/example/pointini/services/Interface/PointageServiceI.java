package com.example.pointini.services.Interface;

import com.example.pointini.entities.Pointage;
import com.example.pointini.entities.User;


import java.util.Date;
import java.util.List;

public interface PointageServiceI {
    Pointage findPointageByUserIdAndEtatAndDate(Long idUser, int etat, Date date);


    Date getDateWithoutTimeUsingCalendar();

    void delete(Long id);

    Pointage createPointage(Pointage pointage, Long id);

    Pointage findPointageByUserIdAndEtat(Long idUser,int etat);



    Pointage updatePointage(Pointage pointage, Long id);

    List<Pointage> getAllPointage();

    Pointage checkPointage(Long idUser);

    int findPointageByUserIdEtat(Long id);

    Pointage createPointageEntreSotrie(Pointage pointage, Long idUser);
    Pointage findPointageByDateAndUserId(Date date, Long idUser);

    Pointage findPointageById(Long id);

    List<Pointage> PointageByUserId(Long id);

    Pointage updateP(Pointage p, Long id);
}
