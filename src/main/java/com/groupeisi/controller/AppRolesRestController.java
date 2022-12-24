package com.groupeisi.controller;

import com.groupeisi.dto.AppRoles;
import com.groupeisi.service.AppRolesService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class AppRolesRestController {
    private AppRolesService appRolesService;

    @GetMapping
    public List<AppRoles> getAppRoles(){
        return appRolesService.getAppRoles();
    }

    @GetMapping("/{id}")
    public AppRoles getAppRole(@PathVariable("id") int id){
        return appRolesService.getAppRole(id);
    }

    @GetMapping("/nom/{nom}")
    public AppRoles getAppRole(@PathVariable("nom") String nom){
        return appRolesService.getAppRole(nom);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AppRoles createAppRoles(@Valid @RequestBody AppRoles appRoles){
        return appRolesService.createAppRole(appRoles);
    }

    @PutMapping("/{id}")
    public AppRoles updateAppRoles(@PathVariable("id") int id, @Valid @RequestBody AppRoles appRoles){
        return appRolesService.updateAppRole(id, appRoles);
    }

    @DeleteMapping("/{id}")
    public void deleteAppRole(@PathVariable("id") int id){
        appRolesService.deleteAppRole(id);
    }
}
