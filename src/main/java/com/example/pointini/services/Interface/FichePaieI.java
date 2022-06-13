package com.example.pointini.services.Interface;

import com.example.pointini.entities.FichePaie;

import java.time.LocalTime;


public interface FichePaieI {
    double HeureRetard(Long idUser);


    LocalTime heureMinReatard(Long idUser);

    double HeureTravaille(Long idUser);

    FichePaie createFichepaie(FichePaie fichePaie);

    FichePaie findFichePaieById(Long id);
    LocalTime heureMin(Long idUser);
}
