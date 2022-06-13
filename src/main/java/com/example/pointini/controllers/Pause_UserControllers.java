package com.example.pointini.controllers;


import com.example.pointini.entities.Pause_User;
import com.example.pointini.services.Pause_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/addPauseToUser")
public class Pause_UserControllers {

    @Autowired
    public Pause_UserService pause_userService;
    @PostMapping(path = "/{idPause}/{idUser}" )
    public Pause_User addPauseUser(@PathVariable Long idPause, @PathVariable Long idUser){
        return pause_userService.addPause_User(idPause,idUser);
    }
}
