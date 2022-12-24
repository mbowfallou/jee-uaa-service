package com.groupeisi.dao;

import com.groupeisi.entities.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAppUserRepository extends JpaRepository<AppUserEntity, Integer> {
    AppUserEntity findByEmail(String email);
    List<AppUserEntity> findByNom(String nom);
    List<AppUserEntity> findByPrenom(String prenom);
    List<AppUserEntity> findByPrenomAndNom(String prenom, String nom);
    AppUserEntity findByEmailAndPassword(String email, String password);
    /*
    List<AppUserEntity> findByPrenomOrNom(String prenom_ou_nom);
    List<AppUserEntity> findByEtat(String etat);*/
}
