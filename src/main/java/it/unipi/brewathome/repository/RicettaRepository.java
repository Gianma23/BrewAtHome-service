package it.unipi.brewathome.repository;

import it.unipi.brewathome.models.Account;
import it.unipi.brewathome.models.Ricetta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Utente
 */

@Repository
public interface RicettaRepository extends CrudRepository<Ricetta, Integer> {
    
    Iterable<Ricetta> findByAccountId(String accountId);
    Ricetta findById(int id);
}
 