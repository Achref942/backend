package com.example.pointini.services;

import com.example.pointini.dto.UserEntrepriseRegisterDto;
import com.example.pointini.entities.*;
import com.example.pointini.repository.FichePaieRepository;
import com.example.pointini.repository.PointgeRepository;
import com.example.pointini.repository.UserRepository;
import com.example.pointini.services.Interface.UserServiceI;
import com.example.pointini.utils.StorgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class UserService implements UserServiceI {
    @Autowired
    public PointgeRepository pointgeRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleService roleService;


    @Autowired
    public EntrepriseService entrepriseService;

    @Autowired
    public FichePaieRepository fichePaieRepository;

    @Autowired
    public PackService packService;

    @Autowired
    public StorgeService storgeService;

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by("email").ascending());
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User createUser(User u, MultipartFile file) {
        String fileName=storgeService.CreateNameImage(file);
        storgeService.store(file,fileName);
        if (fileName==null) u.setPhoto("téléchargement-77317162.png");
        else u.setPhoto(fileName);
         u.setDate(this.getDateWithoutTimeUsingCalendar());
         u.setEtat(1);
        return userRepository.save(u);
    }

    @Override
    public User UpdateUser(User u, Long idUser) {
        Optional<User> utOptional=userRepository.findById(idUser);
        if(utOptional==null) return null;
        else return userRepository.save(u);

    }
    @Override
    public User UpdateUser2(User u, Long idUser) {
        Optional<User> utOptional=userRepository.findById(idUser);
        if(utOptional==null) return null;
        else return userRepository.save(u);

    }

    @Override
    public User updateUser(User u) {

            return userRepository.save(u);
    }


    @Override
    public void delete (Long id){
 userRepository.deleteById(id);
    }



    @Override
    public List<User> findByRole(String libelle) {
        return userRepository.findByRole(libelle);
    }

    @Override
    public User addRoleUser(Long idUser, Long idRole) {
        User user = this.findUserById(idUser);
        Role role =this.roleService.findRoleById(idRole);
        user.setRole(role);
        return this.updateUser(user);
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @Override
    public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findByPassword(String password) {
        return userRepository.findByPassword(password);
    }

    @Override
    public User addEntrepriseUser(Long idUser, Long idEntreprise){
        User user=this.findUserById(idUser);
        Entreprise entreprise=entrepriseService.findEntrepriseById(idEntreprise);
        user.setEntreprise(entreprise);
        return this.updateUser(user);
    }
  @Override
    public User addFichePaieUser(Long idUser, Long idFichePaie) {
        User user = this.findUserById(idUser);
        FichePaie fichePaie = fichePaieRepository.findFichePaieById(idFichePaie);
        user.setFichePaie(fichePaie);
        return this.updateUser(user);

    }

    @Override
    public Date getDateWithoutTimeUsingCalendar()
    {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.add(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    @Override
    public List<User> findByEntreprise(Long id)
    {
        Entreprise entreprise=entrepriseService.findEntrepriseById(id);
        List<User> users=entreprise.getUser();

        return users;
    }




    @Override
    public UserEntrepriseRegisterDto createuserEntrepriseRegister(UserEntrepriseRegisterDto userEntrepriseRegister) {
        return null;
    }


    @Override
    public ResponseEntity<Resource> getFile(String filename) {
        Resource file = storgeService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }




//    @Override
//    public UserEntrepriseRegisterDto createuserEntrepriseRegister(User user ,Entreprise entreprise) {
//        UserEntrepriseRegisterDto userEntrepriseRegister =new UserEntrepriseRegisterDto();
//        user.setEmail(userEntrepriseRegister.getEmail());
//        user.setFirstName(userEntrepriseRegister.getFirstName());
//        user.setLastName(userEntrepriseRegister.getLastName());
//        user.setPassword(userEntrepriseRegister.getPassword());
//        user.setNumTel(userEntrepriseRegister.getNumTel());
//        entreprise.setLibelle(userEntrepriseRegister.getLibelle());
//        Role role = roleService.findByLibelle("Admin");
//        user.setRole(role);
//        Pack pack = packService.findByLibelle("7--Free");
//        this.addEntrepriseUser(user.getId(),entreprise.getId());
//        this.entrepriseService.AddPackToEntreprise(pack.getId(),entreprise.getId());
//        this.entrepriseService.createEntreprise(entreprise);
//        this.createUser(user);
//        return  userEntrepriseRegister;
//    }
//
//    public List<UserEntrepriseRegisterDto> getAllEvnts(){
//        return userRepository.findAll()
//                .stream()
//                .map(this::createuserEntrepriseRegister,this::createuserEntrepriseRegister)
//                .collect(Collectors.toList());
//    }






    @Override
    public List<User> presence(Long id) {
        List<User> users = this.findByEntreprise(id);
        List<User> usersp=new ArrayList<>();
        users.stream().forEach(user -> {
                                if (user.getPresence()==1) {
                                    System.out.println("user présent");
                                    usersp.add(user);
                                }
                }
        );
        return usersp;
    }

    @Override
    public List<User> abscent(Long id) {
        List<User> users = this.findByEntreprise(id);
        List<User> usersp=new ArrayList<>();
        users.stream().forEach(user -> {
                    if (user.getPresence()==0) {
                        System.out.println("user absent");
                        usersp.add(user);
                    }
                }
        );
        return usersp;
    }

    @Override
    public User activer(Long id){
        User user=this.findUserById(id);
        user.setEtat(1);
        this.updateUser(user);
        return user;
    }

    @Override
    public User desactiver(Long id){
        User user=this.findUserById(id);
        user.setEtat(0);
        this.updateUser(user);
        return user;
    }








}
