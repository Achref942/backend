package com.example.pointini.services.Interface;

import com.example.pointini.entities.Operation;
import com.example.pointini.entities.User;

import java.util.List;

public interface OperationServiceI {
    void delete(Long id);


    Operation CreateOperationAvance(Operation operation, Long idUser);

    Operation CreateOperationPrime(Operation operation, Long idUser);

    Operation confimeAvance(Long id);

    Operation CreateOperationPenalité(Operation operation, Long idUser);

    List<Operation> getAllPrime(Long idE);




    List<Operation> getAllAvance(Long idE);

    List<Operation> getAllPenalite(Long idE);


    List<User> addPrimforAll(Operation operation, Long idE);

    List<User> addPenaliteforAll(Operation operation, Long idE);

    Operation UpdateOperation(Operation operation);

    Operation findOperationByid(Long id);

    Operation addOperationToUser(Long idOperation, Long idUser);
}
