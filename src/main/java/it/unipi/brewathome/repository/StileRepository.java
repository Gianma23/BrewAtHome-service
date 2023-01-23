package it.unipi.brewathome.repository;

import it.unipi.brewathome.models.Stile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StileRepository extends CrudRepository<Stile, Integer> {
    
    Iterable<Stile> findByGuida(String guida);
    Stile findByNome(String nome);
}
 