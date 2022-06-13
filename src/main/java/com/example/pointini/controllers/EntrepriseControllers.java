package com.example.pointini.controllers;

import com.example.pointini.entities.Entreprise;
import com.example.pointini.entities.Pack;
import com.example.pointini.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/entreprise")
public class EntrepriseControllers {
    @Autowired
    public EntrepriseService entrepriseService;

    //Create Entreprise
    @PostMapping(path = "/")
    public Entreprise createEntreprise(@RequestBody Entreprise entreprise){
        return entrepriseService.createEntreprise(entreprise);
    }

    //Update entreprise
    @PutMapping(path = "/update/{idE}")
    public Entreprise updateEntreprise(@RequestBody Entreprise entreprise,@PathVariable Long idE){
        return  entrepriseService.updateEntreprise(entreprise,idE);
    }

    //Get All Entreprises
    @GetMapping(path = "/")
    public List<Entreprise> getAllEntreprise(){
        return entrepriseService.getAllEntreprise();
    }

    //Find Entreprise By Id
    @GetMapping(path = "/findEntrepriseById/{IdEntreprise}")
    public Entreprise findEntrepriseById(@PathVariable Long IdEntreprise){
        return entrepriseService.findEntrepriseById(IdEntreprise);
    }

    @GetMapping(path="/AddPackToEntreprise/{idPack}/{idEntreprise}")
    public Pack AddPackToEntreprise (@PathVariable Long idPack, @PathVariable Long idEntreprise){
        return entrepriseService.AddPackToEntreprise(idPack,idEntreprise);
    }

    @GetMapping(path = "/Desactive/{idEntreprise}")
    public Entreprise DesactiveE(@PathVariable Long idEntreprise)
    {
        return entrepriseService.Desactive(idEntreprise);
    }

    @GetMapping(path = "/Active/{idEntreprise}")
    public Entreprise ActiveE(@PathVariable Long idEntreprise)
    {
        return entrepriseService.Active(idEntreprise);
    }
    @DeleteMapping(path = "/delete/{id}")
    public  void delete(@PathVariable  Long id){
        entrepriseService.delete(id);
    }
}
