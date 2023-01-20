package it.unipi.brewathome.repository;

import it.unipi.brewathome.models.Ricetta;
import it.unipi.brewathome.models.Stile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Utente
 */

@Repository
public interface StileRepository extends CrudRepository<Stile, Integer> {
    
    Iterable<Stile> findByGuida(String guida);
}
 