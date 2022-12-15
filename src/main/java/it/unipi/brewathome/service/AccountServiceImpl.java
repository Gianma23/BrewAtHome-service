/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.brewathome.service;

import it.unipi.brewathome.Account;
import it.unipi.brewathome.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Utente
 */
@Service
public class AccountServiceImpl implements AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    //@Autowired
   // private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public void save(Account account) {
       // account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }
    
    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
}
