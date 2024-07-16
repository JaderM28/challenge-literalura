package com.valhalla.challenge_literalura.respository;

import com.valhalla.challenge_literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibrosRepository extends JpaRepository<Libros, Long> {

    List<Libros> findByIdiomasContainsIgnoreCase(String idioma);

}
