package com.example.pointini.services.Interface;

import com.example.pointini.entities.Conge;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface CongeServiceI {


    Date testconge(int nbJour);
    Boolean checkDate(Long idUser, Date datedeb, Date datefin);

    Conge createConge(Conge conge);

    void delete(Long id);

    List<Conge> getAllConge();

    Date getDateWithoutTimeUsingCalendar();

    Long getDuree(Date deb, Date fin);

    Conge DemmandeCongeUser(Long idUser, int nbJour);

    Conge confirmConge(Long id);

    Conge updateConge(Conge conge, Long id);

    Conge findCongeById(Long id);
}
