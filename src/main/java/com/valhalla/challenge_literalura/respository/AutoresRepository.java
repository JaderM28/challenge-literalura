package com.valhalla.challenge_literalura.respository;

import com.valhalla.challenge_literalura.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AutoresRepository extends JpaRepository<Autores, Long> {

    @Query("SELECT a FROM Autores a WHERE a.fechaDeNacimiento <= :anio AND (a.fechaDeMuerte IS NULL OR a.fechaDeMuerte > :anio)")
    List<Autores> findAutoresVivosEnAnio(@Param("anio") String anio);

}
