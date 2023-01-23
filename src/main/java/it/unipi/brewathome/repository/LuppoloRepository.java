package it.unipi.brewathome.repository;

import it.unipi.brewathome.models.Luppolo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LuppoloRepository extends CrudRepository<Luppolo, Integer> {
    
    Iterable<Luppolo> findByRicettaId(int ricettaId);
    Luppolo findById(int id);
}
 