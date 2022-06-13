package com.example.pointini.controllers;
import com.example.pointini.entities.Pointage;
import com.example.pointini.services.PointageService;
import com.example.pointini.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/pointage")
public class PointageControllers {
    @Autowired
    public PointageService pointageService;
    @Autowired
    public UserService userService;

    @GetMapping(path="/findPointageById/{id}")
    public Pointage findPointageById(@PathVariable Long id){
        return pointageService.findPointageById(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public  void delete(@PathVariable  Long id){
        pointageService.delete(id);
    }
    //get all users
    @GetMapping(path = "/")
    public List<Pointage> getAllPointage() {
        return pointageService.getAllPointage();
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   @PutMapping (path = "/createPointage/{idUser}")
   public Pointage createPointage(@RequestBody Pointage pointage, @PathVariable Long idUser) {
       return pointageService.createPointage(pointage,idUser);
   }
   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   @PutMapping(path = "/updateP/{id}")
   public Pointage updatePointage(@RequestBody Pointage pointage, @PathVariable Long id) {
       return pointageService.updateP(pointage,id);
   }
        @GetMapping(path = "/checkPointage/{idUser}")
    public Pointage checkPointage(@PathVariable Long idUser) {

        return pointageService.checkPointage(idUser);
    }

    @GetMapping(path = "/find/{idUser}")
    public Pointage findPointageByUserId(@PathVariable Long idUser) {
        return pointageService.findPointageByUserIdAndEtat(idUser, 0);
    }

    @GetMapping(path = "/findAllPointage")
    public List<Pointage> findAllPointage(){
        return pointageService.getAllPointage();
    }

    @GetMapping(path = "/findPointageByDate/{date}/{idUser}")
    public Pointage findPointageByDateAndUserId(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date, @PathVariable Long idUser)
    {
        return pointageService.findPointageByDateAndUserId(date,idUser);
    }
    @GetMapping(path = "/pointagelist/{id}")
    public List<Pointage> pointagelist(@PathVariable Long id)
    {
        return pointageService.PointageByUserId(id);
    }
    @GetMapping(path="/findbyUserId/{id}")
    public  List<Pointage> findPointagesByUserId (@PathVariable Long id){
        return pointageService.PointageByUserId(id);
    }

    @GetMapping(path = "/findPointageByUserIdEtat/{id}")
    public int findPointageByUserIdEtat(@PathVariable Long id){
        return pointageService.findPointageByUserIdEtat(id);
    }
}

