package com.example.pointini.services.Interface;

import com.example.pointini.entities.Entreprise;
import com.example.pointini.entities.Pack;

import java.util.List;

public interface EntrepriseServiceI {
    List<Entreprise> getAllEntreprise();

    Entreprise  findEntrepriseById(Long id);

    Entreprise createEntreprise(Entreprise entreprise);


    Entreprise updateEntreprise(Entreprise entreprise, Long idE);

    void delete(Long id);

    Pack AddPackToEntreprise(Long idPack, Long idEntreprise);

    String getDateWithoutTimeUsingCalendar();

    Entreprise DesactiverEntreprise(Long idEntreprise);

    Entreprise Desactive(Long idE);

    Entreprise Active(Long idE);
}
