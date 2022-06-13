package com.example.pointini.controllers;
import com.example.pointini.entities.FichePaie;

import com.example.pointini.services.FichePaieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;


@RestController
@RequestMapping(path = "/fichepaie")
public class FichePaieControllers {

    @Autowired
    public FichePaieService fichePaieService;

    @PostMapping(path = "/create")
    public FichePaie createUser(@RequestBody FichePaie fichePaie) {

        return fichePaieService.createFichepaie(fichePaie);
    }

    @GetMapping(path="/{idUser}")
    public double HeureTravaille(@PathVariable Long idUser)
    {
        return fichePaieService.HeureTravaille(idUser);
    }
    @GetMapping(path = "/heureretard/{idUser}")
    public double HeureRetard(@PathVariable Long idUser)
    {
        return fichePaieService.HeureRetard(idUser);
    }

}
