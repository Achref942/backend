package com.example.pointini.services;

import com.example.pointini.entities.Enum.ConfinoConfir;
import com.example.pointini.entities.Enum.TypeOperation;
import com.example.pointini.entities.Operation;
import com.example.pointini.entities.User;
import com.example.pointini.repository.OperationRepository;
import com.example.pointini.services.Interface.OperationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.pointini.entities.Enum.SensOperation.augmenter;
import static com.example.pointini.entities.Enum.SensOperation.diminuer;
import static com.example.pointini.entities.Enum.TypeOperation.*;

@Service
public class OperationService implements OperationServiceI {
    @Autowired
    public OperationRepository operationRepository;
    @Autowired
    public UserService userService;
    @Autowired
    public EntrepriseService entrepriseService;

    @Override
    public void delete(Long id) {
        operationRepository.deleteById(id);
    }

    @Override
    public Operation CreateOperationAvance(Operation operation, Long idUser) {
        operation.setTypeOperation(avance);
        operation.setEtat(ConfinoConfir.en_cours);
        operation.setSensOperation(diminuer);
        operationRepository.save(operation);
        return this.addOperationToUser(operation.getId(), idUser);
    }
    @Override
    public Operation confimeAvance(Long id){
        Operation operation=findOperationByid(id);
        operation.setEtat(ConfinoConfir.confirmer);
        return operationRepository.save(operation);
    }

    @Override
    public Operation CreateOperationPenalité(Operation operation, Long idUser) {
        operation.setTypeOperation(penalite);
        operation.setSensOperation(diminuer);
        operationRepository.save(operation);
        return this.addOperationToUser(operation.getId(), idUser);
    }

    @Override
    public Operation CreateOperationPrime(Operation operation, Long idUser) {
        operation.setTypeOperation(prime);
        operation.setSensOperation(augmenter);
        operationRepository.save(operation);
        return this.addOperationToUser(operation.getId(), idUser);
    }








    @Override
    public List<Operation> getAllPrime(Long idE) {
        List<Operation> operations = operationRepository.findAll();
        List<Operation> prime = new ArrayList<>();
        operations.stream().forEach(operation -> {
                    if (operation.getUser().getEntreprise()== null) {
                        System.out.println("null");
                    }
           else {
                System.out.println("not null");
                        if (operation.getTypeOperation() == TypeOperation.prime && operation.getUser().getEntreprise().getId() == idE) {
                            prime.add(operation);
                        }
            }
                }
        );
        return prime;
    }



    @Override
        public List<Operation> getAllAvance(Long idE) {
        List<Operation> operations = operationRepository.findAll();
        List<Operation> avance = new ArrayList<>();
        operations.stream().forEach(operation -> {
                    if (operation.getUser().getEntreprise()== null) {
                        System.out.println("null");
                    }
                    else {
                        System.out.println("not null");
                        if (operation.getTypeOperation() == TypeOperation.avance && operation.getUser().getEntreprise().getId() == idE) {
                            avance.add(operation);
                        }
                    }
                }
        );
        return avance;
    }
    @Override
        public List<Operation> getAllPenalite(Long idE) {
        List<Operation> operations = operationRepository.findAll();
        List<Operation> penalite = new ArrayList<>();
        operations.stream().forEach(operation -> {
                    if (operation.getUser().getEntreprise()== null) {
                        System.out.println("null");
                    }
                    else {
                        System.out.println("not null");
                        if (operation.getTypeOperation() == TypeOperation.penalite && operation.getUser().getEntreprise().getId() == idE) {
                            penalite.add(operation);
                        }
                    }
                }
        );
        return penalite;
    }
    @Override
    public List<User> addPrimforAll(Operation operation, Long idE){
        List<User> users=userService.findByEntreprise(idE);
        users.stream().forEach(user -> {

            this.CreateOperationPrime(operation,user.getId());
        });
        return users;
    }
    @Override
    public List<User> addPenaliteforAll(Operation operation, Long idE){
        List<User> users=userService.findByEntreprise(idE);

        users.stream().forEach(user -> {

            this.CreateOperationPenalité(operation,user.getId());
        });
        return users;
    }

    //prime for All Users en cours...

    // @Override
    // public Operation CreateOperationForAll(Operation operation,Long idEntreprise) {
    //     Entreprise entreprise= entrepriseService.findEntrepriseById(idEntreprise);
    //     List<User> users=entreprise.getUser();
    //
    // }

    //prime for All Users en cours...
//   @Override
//   public Operation CreateOperationPrimeForAll(Operation operation, Long idUser){
//       operation.setTypeOperation(prime);
//       operation.setSensOperation(augmenter);
//       operationRepository.save(operation);
//       return  this.addOperationToUser(operation.getId(),idUser);
//   }


    @Override
    public Operation UpdateOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public Operation findOperationByid(Long id) {
        return operationRepository.findById(id).get();
    }

    @Override
    public Operation addOperationToUser(Long idOperation, Long idUser) {
        Operation operation = this.findOperationByid(idOperation);
        User user = userService.findUserById(idUser);
        double s = user.getSalaire();
        if (operation.getTypeOperation() == TypeOperation.penalite || operation.getTypeOperation() == TypeOperation.avance) {
            s = s - operation.getMontant();
            user.setSalaire(s);
            userService.updateUser(user);
            operation.setUser(user);
            return operationRepository.save(operation);
        } else {
            s = s + operation.getMontant();
            user.setSalaire(s);
            userService.updateUser(user);
            operation.setUser(user);
            return operationRepository.save(operation);
        }
    }

}
