package com.example.pointini.services.Interface;

import com.example.pointini.entities.Role;
import com.example.pointini.entities.User;

import java.util.List;

public interface RoleServiceI {
    List<Role> getAllRole();

    Role findRoleById(Long id);

    Role CreateRole(Role r);

    void delete(Long id);

    Role updateRole(Role r, Long id);

    Role findByLibelle(String s);

    List<User>  findUserByRole(Long idR);

    List<User>  findUserByRoleEntreprise(Long idR, Long idE);
}
