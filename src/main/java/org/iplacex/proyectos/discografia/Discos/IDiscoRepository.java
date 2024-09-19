package org.iplacex.proyectos.discografia.Discos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface IDiscoRepository extends MongoRepository<Discos, String> {
    @Query("{ 'idArtista': ?0 }")
    List<Discos> findDiscosByIdArtista(String idArtista);
}
