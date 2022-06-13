package com.example.pointini.controllers;

import com.example.pointini.entities.User;
import com.example.pointini.services.UserPDFExporter;
import com.example.pointini.services.UserService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController

@RequestMapping(path = "/user")
public class UserControllers {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserService userService;

    //get all users
    @GetMapping(path = "/")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    //find uder by id
    @GetMapping(path = "/{id}")
    public User findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    //create user
    @PostMapping(path = "/")
    public User createUser( User u, @RequestParam MultipartFile file) {
        String cryptedPassword = bCryptPasswordEncoder.encode(u.getPassword());
        u.setPassword(cryptedPassword);
        if (file==null){
        }
        return userService.createUser(u,file);
    }
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        return userService.getFile(filename);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }


    @PutMapping(path="/update/{idUser}")
    public User UpdateUser(@RequestBody User u,@PathVariable Long idUser) {
        String cryptedPassword = bCryptPasswordEncoder.encode(u.getPassword());
        u.setPassword(cryptedPassword);
        return userService.UpdateUser2(u,idUser);
    }

    //add role to user
    @GetMapping(path = "/addRole/{idUser}/{idRole}")
    public User addRoleUser(@PathVariable Long idUser, @PathVariable Long idRole) {
        return userService.addRoleUser(idUser, idRole);
    }

    // find user by firstname
    @GetMapping(path = "/findByFirstName/{firstName}")
    public ResponseEntity<List<User>> findUserByFirstName(@PathVariable String firstName) {

        List<User> users = userService.findByFirstName(firstName);
        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }

    }

//    //find user by firstname and lastname
//    @GetMapping(path = "/findByFirstNameandLastName/{firstName}/{lastName}")
//    public ResponseEntity<List<User>> findByFirstNameandLastName(@PathVariable String firstName, @PathVariable String lastName) {
//
//        List<User> users = userService.findByFirstNameAndLastName(firstName, lastName);
//        if (users.isEmpty()) {
//            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
//        }
//
//    }



    //find user by email just one user
    @GetMapping(path = "/findByEmail/{email}")
    public User findByEmail(@PathVariable String email) {

        User user = userService.findByEmail(email);
        return user;

    }


    // find by password all the users who have the same password
    @GetMapping(path = "/findByPassword/{password}")
    public ResponseEntity<List<User>> findByPassword(@PathVariable String password) {

        List<User> users = userService.findByPassword(password);
        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }

    }


    // just Test
    @GetMapping(path = "/date")
    public void Date() {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());

    }


    @PostMapping(path = "/login")
    public ResponseEntity<User> Login (@RequestBody User user) {

        User user2 = userService.findByEmail(user.getEmail());
        BCryptPasswordEncoder cryptedPassword = new BCryptPasswordEncoder();
        if (cryptedPassword.matches(user.getPassword(),user2.getPassword()) && user2.getEtat()==1) {

            System.out.println("Login avec succè");
            return new ResponseEntity<User>(user2,HttpStatus.OK);
        } else {
            System.out.println("erreur de connection");
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping(path = "/addEntrepriseUser/{idUser}/{idEntreprise}")
    public User addEntrepriseUser(@PathVariable Long idUser, @PathVariable Long idEntreprise) {
        return userService.addEntrepriseUser(idUser,idEntreprise);
    }
   @PostMapping(path = "/addFichePaieUser/{idUser}/{idFichePaie}")
    public User addFichePaieUser(Long idUser, Long idFichePaie)
    {
        return userService.addFichePaieUser(idUser,idFichePaie);
    }


    @GetMapping(path = "/findByEntreprise/{id}")
    public List<User> findByEntreprise(@PathVariable Long id)
    {

        return userService.findByEntreprise(id);
    }

    @GetMapping(path = "/present/{id}")
    public List<User> presence(@PathVariable Long id)
    {
        return userService.presence(id);
    }

    @GetMapping(path = "/abscent/{id}")
    public List<User> abscent(@PathVariable Long id)
    {
        return userService.abscent(id);
    }



//   @PostMapping(path="/")
//   public UserEntrepriseRegisterDto xxcxx(@RequestBody UserEntrepriseRegisterDto userEntrepriseRegister){
//       return userService.createuserEntrepriseRegister(userEntrepriseRegister);
//   }



    @GetMapping(path="/activer/{id}")
    public User activer(@PathVariable Long id){
        return userService.activer(id);
}
    @GetMapping(path="/desactiver/{id}")
    public User desactiver(@PathVariable Long id){
        return userService.desactiver(id);
    }






    @GetMapping("/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<User> listUsers = userService.getAllUser();

        UserPDFExporter exporter = new UserPDFExporter(listUsers);
        exporter.export(response);

    }

    @GetMapping("/export/OnUser/pdf/{id}")
    public void exportToPDF(HttpServletResponse response, @PathVariable Long id) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        User user = userService.findUserById(id);

        UserPDFExporter exporter = new UserPDFExporter(user);
        exporter.export(response);

    }



}







