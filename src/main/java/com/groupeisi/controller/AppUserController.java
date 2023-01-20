package com.groupeisi.controller;

import com.groupeisi.dto.AppUser;
import com.groupeisi.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    // Get All Users
    @GetMapping
    public List<AppUser> getAppUsers(){
        return appUserService.getAppUsers();
    }

    // Get One User By his ID
    @GetMapping("/{id}")
    public AppUser getAppUser(@PathVariable("id") int id){
        return appUserService.getAppUser(id);
    }

    // Get One User By his Firstname
    @GetMapping("/prenom/{prenom}")
    public List<AppUser> getAppUserByFirstname(@PathVariable("prenom") String firstname){
        return appUserService.getAppUserByFirstname(firstname);
    }

    // Get One User By his Lastname
    @GetMapping("/nom/{nom}")
    public List<AppUser> getAppUserByLastname(@PathVariable("nom") String lastname){
        return appUserService.getAppUserByLastname(lastname);
    }

    // Get One User By his Email
    @GetMapping("/email/{email}")
    public AppUser getAppUserByEmail(@PathVariable("email") String email){
        return appUserService.getAppUserByEmail(email);
    }

    // Get One User By First and Last Name
    @GetMapping("/nomcomplet/{prenom}/{nom}")
    public List<AppUser> getAppUserByPrenomAndNom(@PathVariable("prenom") String firstname, @PathVariable("nom") String lastname){
        return appUserService.getAppUserByPrenomAndNom(firstname, lastname);
    }

    // Get One User By Email and Password
    @GetMapping("/account/{email}/{password}")
    public AppUser getAppUserByEmailAndPassword(@PathVariable("email") String email, @PathVariable(value = "", name = "password") String password){
        return appUserService.getAppUserByEmailAndPassword(email, password);
    }

    // Save A User
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AppUser createAppUser(@Valid @RequestBody AppUser appUser){
        return appUserService.createAppUser(appUser);
    }

    // Update A User By his ID
    @PutMapping("/{id}")
    public AppUser updateAppUser(@PathVariable("id") int id, @Valid @RequestBody AppUser appUser){
        return appUserService.updateAppUser(id, appUser);
    }

    // Remove A User By his ID
    @DeleteMapping("/{id}")
    public void deleteAppUser(@PathVariable("id") int id){
        appUserService.deleteAppUser(id);
    }
}
