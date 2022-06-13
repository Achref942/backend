package com.example.pointini.controllers;

import com.example.pointini.entities.Role;
import com.example.pointini.entities.User;
import com.example.pointini.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/role")
public class RoleControllers {
    @Autowired
    public RoleService roleService;
    @DeleteMapping(path = "/delete/{id}")
    public  void delete(@PathVariable  Long id){
        roleService.delete(id);
    }
    @GetMapping(path = "/")
    public List<Role> getAllRole() {
        return roleService.getAllRole();
    }
    @GetMapping(path="/{id}")
    public Role findRoleById(@PathVariable Long id) {
        return roleService.findRoleById(id);
    }
    @PostMapping(path = "/")
    public Role createRole(@RequestBody Role r) {
        return roleService.CreateRole(r);
    }
    @PutMapping(path="/update/{id}")
    public Role updateRole (@RequestBody Role u,@PathVariable Long id) {
        return roleService.updateRole(u,id);
    }
    @DeleteMapping(path = "/{id}")
    public void deleteRole(@PathVariable Long Id){
        roleService.delete(Id);
    }
    //test
    @GetMapping(path="/getRoleByLibelle/{s}")
    public Role findByLibelle(@PathVariable String s) {
        return roleService.findByLibelle(s);
    }
    //Super Admin
    @GetMapping(path = "/findUserByRole/{id}")
    public List<User> findUserByRole(@PathVariable Long id){
        return roleService.findUserByRole(id);
    }
    //Admin
    @GetMapping(path = "/findUserByRoleEntreprise/{idR}/{idE}")
    public List<User> findUserByRoleEntreprise(@PathVariable Long idR,@PathVariable Long idE){
        return roleService.findUserByRoleEntreprise(idR,idE);
    }

}
