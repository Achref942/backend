package com.example.pointini.services;

import com.example.pointini.entities.Pause;
import com.example.pointini.repository.PauseRepository;
import com.example.pointini.services.Interface.PauseServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.pointini.entities.Enum.HeureMiniute.Heure;

@Service
public class PauseService implements PauseServiceI {
    @Autowired
    public PauseRepository pauseRepository;


    @Override
    public void delete(Long id){
        pauseRepository.deleteById(id);
    }
    @Override
    public Date createPauseHour(int duree){
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR,duree);
cal.add(Calendar.HOUR,1);
        java.util.Date expirationDate = cal.getTime();
        return  expirationDate;
    }
    @Override
    public Date createPauseMinute(int duree){
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE,duree);
        cal.add(Calendar.HOUR,1);
        java.util.Date expirationDate = cal.getTime();


        return  expirationDate;
    }

    @Override
    public Pause createPause(Pause pause) {
        if(pause.getType() ==Heure)
        {
            pause.setExpirationPause(createPauseHour(pause.getDuree()));
            return pauseRepository.save(pause) ;
        }
        else
        {
            pause.setExpirationPause(createPauseMinute(pause.getDuree()));
            return pauseRepository.save(pause);
        }


    }

    @Override
    public Pause updatePause(Pause pause){
        return pauseRepository.save(pause);
    }

    @Override
    public List<Pause> findAllPause(){
        return pauseRepository.findAll();
    }
    @Override
    public Pause findPauseById(Long id) {
        return pauseRepository.findById(id).get();

    }

}
