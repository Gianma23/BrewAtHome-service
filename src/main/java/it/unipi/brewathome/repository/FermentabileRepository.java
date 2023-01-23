package it.unipi.brewathome.repository;

import it.unipi.brewathome.models.Fermentabile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FermentabileRepository extends CrudRepository<Fermentabile, Integer> {
    
    Iterable<Fermentabile> findByRicettaId(int ricettaId);
    Fermentabile findById(int id);
}
 