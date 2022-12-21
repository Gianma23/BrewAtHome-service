/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.brewathome.repository;

import it.unipi.brewathome.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Utente
 */

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    
    Account findByEmail(String email);
    boolean existsByEmail(String email);
}
