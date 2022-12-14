/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.brewathome;

import it.unipi.brewathome.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Utente
 */

public class AccountValidator implements Validator {
      
    @Autowired
    private AccountService accountService;
    
    @Override
    public boolean supports(Class<?> aClass) {
        return Account.class.equals(aClass);
    }
    
    @Override
    public void validate(Object obj, Errors errors) {
        
        Account account = (Account) obj;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if(/*TODO:email validation*/)
            errors.rejectValue("email", "Size.userForm.email");
        if(accountService.findByEmail(account.getEmail()) != null)
            errors.rejectValue("email", "Size.userForm.email");
        
        /*TODO password validation*/
                
    }
}
