package com.example.pointini.services;

import com.example.pointini.entities.FichePaie;
import com.example.pointini.entities.Pointage;
import com.example.pointini.entities.User;
import com.example.pointini.repository.FichePaieRepository;
import com.example.pointini.services.Interface.FichePaieI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;



@Service
public class FichePaieService implements FichePaieI {

    @Autowired
    public FichePaieRepository fichePaieRepository;
    @Autowired
    public UserService userService;
    @Autowired
    public PointageService pointageService;


    @Override
    public double HeureRetard(Long idUser)
    {

        List<Pointage> pointages=pointageService.PointageByUserId(idUser);
        User user=userService.findUserById(idUser);

        pointages.stream().forEach(pointage -> {

            double nbHeure=0;
            LocalDateTime fromDateTime = LocalDateTime.of( pointage.getDateArrive().getYear(), pointage.getDateArrive().getMonth(), pointage.getDateArrive().getDay(), pointage.getArrive().getHour(), pointage.getArrive().getMinute(), pointage.getArrive().getSecond());
            LocalDateTime toDateTime = LocalDateTime.of( user.getDate().getYear(),  user.getDate().getMonth(),  user.getDate().getDay(), user.getHeure_deb().getHour(), user.getHeure_deb().getMinute(), user.getHeure_deb().getSecond());

            LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );

            long years = tempDateTime.until( toDateTime, ChronoUnit.YEARS );
            tempDateTime = tempDateTime.plusYears( years );

            long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS );
            tempDateTime = tempDateTime.plusMonths( months );

            long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS );
            tempDateTime = tempDateTime.plusDays( days );


            long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS );
            tempDateTime = tempDateTime.plusHours( hours );

            long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES );
            tempDateTime = tempDateTime.plusMinutes( minutes );

            long seconds = tempDateTime.until( toDateTime, ChronoUnit.SECONDS );

            nbHeure=hours*60+minutes;
           user.setNbHeureReatardvUser(nbHeure+user.getNbHeureReatardvUser());
        });
        //System.out.println(user.getNbHeureReatardvUser());
        heureMinReatard(idUser);
        return 0;
    }
    @Override
    public LocalTime heureMinReatard(Long idUser) {
        User user=userService.findUserById(idUser);
        Math.abs(user.getNbHeureReatardvUser());
        int min= (int) Math.abs(user.getNbHeureReatardvUser()%60d);
        int heure= (int) Math.abs(user.getNbHeureReatardvUser()/60d);
        LocalTime localTime=LocalTime.of(heure,min);
        System.out.println(localTime);
        return localTime;
    }



    @Override
    public double HeureTravaille(Long idUser) {

        List<Pointage> pointages=pointageService.PointageByUserId(idUser);
        User user=userService.findUserById(idUser);

        pointages.stream().forEach(pointage -> {

            double nbHeure=0;

            LocalDateTime fromDateTime = LocalDateTime.of( pointage.getDateArrive().getYear(), pointage.getDateArrive().getMonth(), pointage.getDateArrive().getDay(), pointage.getArrive().getHour(), pointage.getArrive().getMinute(), pointage.getArrive().getSecond());
            LocalDateTime toDateTime = LocalDateTime.of( pointage.getDateSortir().getYear(),  pointage.getDateSortir().getMonth(),  pointage.getDateSortir().getDay(), pointage.getSortir().getHour(), pointage.getSortir().getMinute(), pointage.getSortir().getSecond());

            LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );

            long years = tempDateTime.until( toDateTime, ChronoUnit.YEARS );
            tempDateTime = tempDateTime.plusYears( years );

            long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS );
            tempDateTime = tempDateTime.plusMonths( months );

            long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS );
            tempDateTime = tempDateTime.plusDays( days );


            long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS );
            tempDateTime = tempDateTime.plusHours( hours );

            long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES );
            tempDateTime = tempDateTime.plusMinutes( minutes );

            long seconds = tempDateTime.until( toDateTime, ChronoUnit.SECONDS );

//            System.out.println( years + " years " +
//                    months + " months " +
//                    days + " days " +
//                    hours + " hours " +
//                    minutes + " minutes " +
//                    seconds + " seconds.");

            nbHeure=hours*60+minutes;
            user.setNbHeureTravUser(nbHeure+user.getNbHeureTravUser());
    });
        System.out.println(user.getNbHeureTravUser());
        heureMin(idUser);


        return 0;
    }

    @Override
    public FichePaie createFichepaie(FichePaie fichePaie) {

        return fichePaieRepository.save(fichePaie);
    }
    @Override
    public FichePaie findFichePaieById(Long id) {
        return fichePaieRepository.findById(id).get();
    }

    @Override
    public LocalTime heureMin(Long idUser) {
        User user=userService.findUserById(idUser);
        Math.abs(user.getNbHeureTravUser());
        int min= (int) Math.abs(user.getNbHeureTravUser()%60d);
       int heure= (int) Math.abs(user.getNbHeureTravUser()/60d);
         LocalTime localTime=LocalTime.of(heure,min);
        System.out.println(localTime);
        return localTime;
    }


}
