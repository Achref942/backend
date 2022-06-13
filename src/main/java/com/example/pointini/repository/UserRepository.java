package com.example.pointini.repository;

import com.example.pointini.entities.Entreprise;
import com.example.pointini.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(String libelle);

    List<User> findByFirstName(String firstName);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    User findByEmail(String email);

    List<User> findByPassword(String password);



}
