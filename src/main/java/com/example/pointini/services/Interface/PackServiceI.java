package com.example.pointini.services.Interface;

import com.example.pointini.entities.Pack;

import java.util.Date;
import java.util.List;

public interface PackServiceI {
    List<Pack> getAllPack();

    Pack findPackById(Long id);


    void delete(Long id);

    Date addDateDexpirationMonths(int duree);

    Date addDateDexpirationDays(int duree);

    Pack createPack(Pack pack);

    Pack updatePack(Pack pack, Long IdP);

    Pack findByLibelle(String s);

//    Pack AddPackToEntreprise(Long idPack, Long idEntreprise);


}
