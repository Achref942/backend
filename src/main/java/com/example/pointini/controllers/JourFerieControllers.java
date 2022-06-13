package com.example.pointini.controllers;

import com.example.pointini.entities.JourFerie;
import com.example.pointini.services.JourFerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/jourferie")
public class JourFerieControllers {

    @Autowired
    public JourFerieService jourFerieService;

    @PostMapping(path="/")
    public JourFerie creatJourFerie(@RequestBody JourFerie jourFerie)
    {
        return jourFerieService.createJourFerie(jourFerie);
    }
    @PutMapping(path="/update/{IdJF}")
    public JourFerie updateJourFerie(@RequestBody JourFerie jourFerie,@PathVariable Long IdJF)
    {
        return jourFerieService.updateJourFerie(jourFerie,IdJF);
    }
    @PostMapping(path = "/test")
    public List<JourFerie> testJourFerie()
    {
       return jourFerieService.testJourFerie();
    }

    @GetMapping(path = "/")
    public List<JourFerie> getAllJourFerie()
    {
        return jourFerieService.getAllJourFerie();
    }

    @GetMapping(path="/{id}")
    public JourFerie getJfById(@PathVariable Long id){
        return jourFerieService.findJourFerieById(id);
    }
    @DeleteMapping(path = "/delete/{id}")
    public  void delete(@PathVariable  Long id){
        jourFerieService.delete(id);
    }
}
