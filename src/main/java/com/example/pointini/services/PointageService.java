package com.example.pointini.services;

import com.example.pointini.entities.Pointage;
import com.example.pointini.entities.User;
import com.example.pointini.repository.PointgeRepository;
import com.example.pointini.services.Interface.PointageServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;

@Slf4j
@Service
public class PointageService implements PointageServiceI {
    @Autowired
    public PointgeRepository pointgeRepository;
    @Autowired
    public UserService userService;

    @Override
    public Pointage findPointageByUserIdAndEtat(Long idUser, int etat) {
        return pointgeRepository.findPointageByUserIdAndEtat(idUser, etat);
    }
    @Override
    public Pointage findPointageByUserIdAndEtatAndDate(Long idUser, int etat, Date date) {

        return pointgeRepository.findPointageByUserIdAndEtatAndDate(idUser,etat,date);
    }
    @Override
    public Date getDateWithoutTimeUsingCalendar()
    {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.add(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    @Override
    public void delete (Long id){
        pointgeRepository.deleteById(id);
    }

    @Override
    public Pointage createPointage(Pointage pointage, Long idUser) {
        User user = userService.findUserById(idUser);
        pointage.setUser(user);
        pointage.setArrive(LocalTime.now());
        pointage.setDate(this.getDateWithoutTimeUsingCalendar());
        pointage.setDateArrive(this.getDateWithoutTimeUsingCalendar());
        pointage.setEtat(0);
        user.setPresence(1);
        user.setInOut(true);
        return pointgeRepository.save(pointage);
    }
    @Override
    public Pointage updatePointage(Pointage pointage, Long idUser) {
        User user = userService.findUserById(idUser);
        pointage.setUser(user);
        pointage.setSortir(LocalTime.now());
        pointage.setDateSortir(this.getDateWithoutTimeUsingCalendar());
        pointage.setEtat(1);
        user.setPresence(0);
        user.setInOut(false);
        return pointgeRepository.save(pointage);
    }

    @Override
    public List<Pointage> getAllPointage() {
        return pointgeRepository.findAll();
    }

    @Override
    public Pointage checkPointage(Long idUser) {
        if (pointgeRepository.findPointageByUserIdAndEtat(idUser, 0) != null) {
            Pointage pointage = this.findPointageByUserIdAndEtat(idUser, 0);
            this.updatePointage(pointage, idUser);
            return pointage;
        } else {
            Pointage pointage = new Pointage();
            this.createPointage(pointage, idUser);
            return pointage;
        }
    }

@Override
public int findPointageByUserIdEtat (Long id){
        Pointage pointage=findPointageByUserIdAndEtat(id,0);
        if (pointage!=null){
            return pointage.getEtat();
        }
        else return 1;
}
    @Override
    public Pointage createPointageEntreSotrie(Pointage pointage,Long idUser) {
        User user=userService.findUserById(idUser);
        pointage.setUser(user);
//        pointage.setArrive(user.getHeure_deb());
//        pointage.setSortir(user.getHeure_fin());
        pointage.setDate(this.getDateWithoutTimeUsingCalendar());
 //       pointage.setDateArrive(this.getDateWithoutTimeUsingCalendar());
//         pointage.setDateSortir(this.getDateWithoutTimeUsingCalendar());
        pointage.setEtat(1);
        return pointgeRepository.save(pointage);

    }

    @Override
    public Pointage findPointageByDateAndUserId(Date date,Long idUser) {
        return pointgeRepository.findPointageByDateAndUserId(date,idUser);
    }

    @Override
    public Pointage findPointageById(Long id) {
        return pointgeRepository.findById(id).get();
    }



    @Override
    public List<Pointage> PointageByUserId(Long id){
        return pointgeRepository.findPointageByUserId(id);
    }

    @Override
    public Pointage updateP(Pointage p, Long id) {
        Pointage pointage=pointgeRepository.findById(id).orElse(null);
       pointage.setId(id);
        pointage.setArrive(p.getArrive()==null? pointage.getArrive():p.getArrive());
        pointage.setSortir(p.getSortir()==null? pointage.getSortir():p.getSortir());
        pointage.setDateArrive(p.getDateArrive()==null? pointage.getDateArrive():p.getDateArrive());
        pointage.setDateSortir(p.getDateSortir()==null? pointage.getDateSortir():p.getDateSortir());
        return pointgeRepository.saveAndFlush(pointage);
    }

}


