package com.example.pointini.controllers;

import com.example.pointini.entities.Operation;
import com.example.pointini.entities.User;
import com.example.pointini.services.OperationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/operation")
public class OperationControllers {
    @Autowired
    public OperationService operationService;

    @GetMapping(path = "/")
    public List<Operation> GetAllOperation() {
        return operationService.operationRepository.findAll();
    }

    @PutMapping(path = "/CreatOperationAvance/{idUser}")
    public Operation CreatOperationAvance(@RequestBody Operation operation, @PathVariable Long idUser) {
        return operationService.CreateOperationAvance(operation, idUser);
    }

    @PutMapping(path = "/CreateOperationPrime/{idUser}")
    public Operation CreateOperationPrime(@RequestBody Operation operation, @PathVariable Long idUser) {
        return operationService.CreateOperationPrime(operation, idUser);
    }

    @PutMapping(path = "/CreateOperationPenalite/{idUser}")
    public Operation CreateOperationPenalité(@RequestBody Operation operation, @PathVariable Long idUser) {
        return operationService.CreateOperationPenalité(operation, idUser);
    }

    @PutMapping(path = "/")
    public Operation UpdateOperation(@RequestBody Operation operation) {
        return operationService.UpdateOperation(operation);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable Long id) {
        operationService.delete(id);
    }

    @GetMapping(path = "/getAllPrime/{idE}")
    public List<Operation> getAllPrime(@PathVariable Long idE) {
        return operationService.getAllPrime(idE);
    }

    @GetMapping(path = "/getAllAvance/{idE}")
    public List<Operation> getAllAvance(@PathVariable Long idE) {
        return operationService.getAllAvance(idE);
    }

    @GetMapping(path = "/getAllPenalite/{idE}")
    public List<Operation> getAllPenalite(@PathVariable Long idE) {
        return operationService.getAllPenalite(idE);
    }

    @PutMapping(path = "/addPrimforAll/{idE}")
    public List<User> addPrimforAll(@RequestBody  Operation operation,@PathVariable Long idE){
        return operationService.addPrimforAll(operation,idE);
    }
    @PutMapping(path = "/addPenaliteforAll/{idE}")
    public List<User> addPenaliteforAll(@RequestBody  Operation operation,@PathVariable Long idE){
        return operationService.addPenaliteforAll(operation,idE);
    }
    @GetMapping(path = "/confirmeAvance/{id}")
    public Operation confirmeAvance(@PathVariable Long id){
        return operationService.confimeAvance(id);
    }
}


