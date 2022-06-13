package com.example.pointini.services;

import com.example.pointini.entities.Pause_User;
import com.example.pointini.repository.Pause_UserRepository;
import com.example.pointini.services.Interface.Pause_UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Pause_UserService implements Pause_UserServiceI {
    @Autowired
    public Pause_UserRepository pause_userRepository;
    @Autowired
    public UserService userService;
    @Autowired
    public PauseService pauseService;


    @Override
    public Pause_User addPause_User(Long idUser, Long idPause){
        Pause_User pause_user= new Pause_User();
        pause_user.setPause(pauseService.findPauseById(idPause));
        pause_user.setUser(userService.findUserById(idUser));
        pause_user.setDate(LocalDateTime.now());
            return pause_userRepository.save(pause_user);
    }
}
