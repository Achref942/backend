package com.example.pointini.controllers;

import com.example.pointini.entities.Pack;
import com.example.pointini.services.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController

@RequestMapping(path = "/pack")
public class PackControllers {
    @Autowired
    private PackService packService;

    //Create Pack
    @PostMapping(path="/")
    public Pack createPack(@RequestBody  Pack pack){
        return packService.createPack(pack);
    }
    @DeleteMapping(path = "/delete/{id}")
    public  void delete(@PathVariable  Long id){
        packService.delete(id);
    }
    //Update Pack
    @PutMapping(path="/update/{idp}")
    public Pack updatePack(@RequestBody Pack pack,@PathVariable Long idp){
        return packService.updatePack(pack,idp);
    }

    //Get all packs 
    @GetMapping(path = "/")
    public List<Pack> getAllPack (){
        return packService.getAllPack();
    }

    //Get pack By id
    @GetMapping(path="/{idPack}")
    public Pack getPackById (@PathVariable Long idPack){
        return packService.findPackById(idPack);
    }

    @PostMapping(path = "/addDateDexpirationMonths/{duree}")
    public Date addDateDexpirationMonths(@PathVariable int duree){
        return packService.addDateDexpirationMonths(duree);
    }
    @PostMapping(path = "/addDateDexpirationDays/{duree}")
    public Date addDateDexpirationDays(@PathVariable int duree){
        return packService.addDateDexpirationDays(duree);
    }


}
