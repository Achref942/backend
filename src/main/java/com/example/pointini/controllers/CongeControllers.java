package com.example.pointini.controllers;

import com.example.pointini.dto.EventsDto;
import com.example.pointini.entities.Conge;
import com.example.pointini.services.CongeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/conge")
public class CongeControllers {
    @Autowired
    public CongeService congeService;




    @GetMapping(path = "/events")
    public  List<EventsDto> getAllEvents(){
        return congeService.getAllEvnts();
    }
    @DeleteMapping(path = "/delete/{id}")
    public  void delete(@PathVariable  Long id){
         congeService.delete(id);
    }
    @PutMapping(path = "/update/{id}")
    public Conge updateConge(@RequestBody Conge conge,@PathVariable Long id){
        return congeService.updateConge(conge,id);
    }
    @GetMapping(path = "/{id}")
    public Conge findByIdConge(@PathVariable Long id ){
        return congeService.findCongeById(id);
    }
    @GetMapping(path = "/")
    public List<Conge> getallconge(){
        return congeService.getAllConge();
    }
    @PostMapping(path = "/test")
    public Long xxx(@RequestBody Conge conge){
        return congeService.getDuree(conge.getDatedeb(),conge.getDatefin());
}
    @PostMapping(path="/demmandeconge/{idUser}")
    public Conge demmandeconge(@PathVariable Long idUser, @RequestBody  Conge conge){
        int nbJour = conge.getNbJour();
        return congeService.DemmandeCongeUser(idUser,nbJour);
    }
    @GetMapping(path = "/confirmerConge/{id}")
    public Conge confirmerConge (@PathVariable Long id){
        return congeService.confirmConge(id);
    }
    @PostMapping(path="/")
    public Conge createConge(@RequestBody Conge conge){
        return congeService.createConge(conge);
    }







}
