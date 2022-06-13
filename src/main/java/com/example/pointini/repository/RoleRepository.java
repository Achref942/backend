package com.example.pointini.repository;

import com.example.pointini.entities.Role;
import com.example.pointini.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
 Role findByLibelle(String libelle);
}
