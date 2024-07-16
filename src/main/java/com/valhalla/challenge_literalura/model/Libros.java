package com.valhalla.challenge_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.valhalla.challenge_literalura.dto.AutoresDto;
import com.valhalla.challenge_literalura.dto.LibrosDto;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombreLibro;

    @OneToMany(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Autores> autores;

    private String idiomas;

    private Double cantidadDescargas;


    public Libros(){}

    public Libros(LibrosDto librosDto){

        this.nombreLibro = librosDto.nombreLibro();
        this.autores = librosDto.autores().stream()
                .map(e -> new Autores(e)).collect(Collectors.toList());;
        this.idiomas = librosDto.idiomas().get(0);
        this.cantidadDescargas = librosDto.cantidadDescargas();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public List<Autores> getAutores() {
        return autores;
    }

    public void setAutores(List<AutoresDto> autores) {
         var autor = autores.stream()
                .map(e -> new Autores(e)).collect(Collectors.toList());
        autor.forEach( e -> e.setLibros(this));
        this.autores = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Double cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    @Override
    public String toString() {
        return
                "Titulo: " + nombreLibro +""+
                "Idiomas: " + idiomas +" "+
                "Descargas: " + cantidadDescargas +
                '\n';
    }
}
