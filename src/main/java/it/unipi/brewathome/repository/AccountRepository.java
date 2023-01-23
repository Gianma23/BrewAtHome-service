package it.unipi.brewathome.repository;

import it.unipi.brewathome.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    
    Account findByEmail(String email);
    boolean existsByEmail(String email);
}
