package com.example.pointini.services;

import com.example.pointini.entities.Role;
import com.example.pointini.entities.User;
import com.example.pointini.repository.RoleRepository;
import com.example.pointini.services.Interface.RoleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements RoleServiceI {
    @Autowired
    public RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {

        return roleRepository.findAll();
    }

    @Override
    public Role findRoleById(Long id) {
            return roleRepository.findById(id).get();
    }

    @Override
    public Role CreateRole(Role r) {
        return roleRepository.save(r);
    }
    @Override
    public void delete(Long id) {
      roleRepository.deleteById(id);
    }

    @Override
    public Role updateRole(Role r, Long id) {
        Role rle =roleRepository.findById(id).orElse(null);
        rle.setId(id);
        rle.setLibelle(r.getLibelle()==null ? rle.getLibelle():r.getLibelle());

        return roleRepository.saveAndFlush(rle);
    }
    @Override
    public  Role findByLibelle(String s){
        return roleRepository.findByLibelle(s);
    }

   @Override
    public List<User>  findUserByRole(Long idR){
        Role role=findRoleById(idR);
        List<User> users =role.getUser();
        return users ;
    }
    @Override
    public List<User>  findUserByRoleEntreprise(Long idR,Long idE){
        List<User> users1 =new ArrayList<>();
        Role role=findRoleById(idR);
        List<User> users =role.getUser();
        users.stream().forEach(user -> {
            if (user.getEntreprise().getId()==idE){
                users1.add(user);
            }
        });

            return users1 ;
    }



}
