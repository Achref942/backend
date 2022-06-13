package com.example.pointini.services.Interface;

import com.example.pointini.entities.JourFerie;
import com.example.pointini.entities.User;


import java.util.Date;
import java.util.List;

public interface JourFerieServiceI {


    //Logger logger= LoggerFactory.getLogger(JourFerieService.class);
    void delete(Long id);

    JourFerie createJourFerie(JourFerie jourFerie);
    JourFerie updateJourFerie(JourFerie jourFerie,Long id);
    String getDateWithoutTimeUsingCalendar();

    JourFerie findJFById(Long id);

    Boolean checkDate(Date date, Long idUser);
    List<JourFerie> testJourFerie();
    JourFerie findJourFerieById(Long id);
    List<JourFerie> getAllJourFerie();
}
