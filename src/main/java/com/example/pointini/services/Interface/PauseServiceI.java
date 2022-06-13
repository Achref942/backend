package com.example.pointini.services.Interface;

import com.example.pointini.entities.Pause;

import java.util.Date;
import java.util.List;

public interface PauseServiceI {
    Pause createPause(Pause pause);

    void delete(Long id);

    Date createPauseHour(int duree);

    Date createPauseMinute(int duree);

    Pause updatePause(Pause pause);

    List<Pause> findAllPause();

    Pause findPauseById(Long id);
}
