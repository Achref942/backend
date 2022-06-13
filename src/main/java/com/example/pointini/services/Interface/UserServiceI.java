package com.example.pointini.services.Interface;

import com.example.pointini.dto.UserEntrepriseRegisterDto;
import com.example.pointini.entities.User;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


public interface UserServiceI  {
    List<User> getAllUser();

    List<User> getAllUsers();

    User findUserById(Long id);

   User createUser(User u, MultipartFile file);
    //User createUser(User u);


    User UpdateUser(User u, Long idUser);




    User UpdateUser2(User u, Long idUser);

    User updateUser(User u);

    void delete(Long id);

    List<User> findByRole(String libelle);

    User  addRoleUser (Long idUser,Long idRole);

    List<User>  findByFirstName(String firstName);

    List<User> findByFirstNameAndLastName(String firstName,String lastName);

    User findByEmail(String email);

    List<User> findByPassword(String password);

    User addEntrepriseUser(Long idUser, Long idEntreprise);

    User addFichePaieUser(Long idUser, Long idFichePaie);

    Date getDateWithoutTimeUsingCalendar();

 List<User> findByEntreprise(Long id);


    UserEntrepriseRegisterDto createuserEntrepriseRegister(UserEntrepriseRegisterDto userEntrepriseRegister);

    ResponseEntity<Resource> getFile(String filename);

    List<User> presence(Long id);

    List<User> abscent(Long id);

    User activer(Long id);

    User desactiver(Long id);



    //    @Override
    //    public void delete(Long id) {
    //        userRepository.deleteById(id);
    //    }

    // void delete(Long id);
}
