package com.valhalla.challenge_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.valhalla.challenge_literalura.dto.AutoresDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "autores")

public class Autores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombreAutor;

    private String fechaDeNacimiento;
    private String fechaDeMuerte;

    @ManyToOne
    private Libros libros;

    public Autores(){}

    public Autores(AutoresDto autoresDto){
        this.nombreAutor = autoresDto.nombreAutor();
        this.fechaDeNacimiento = autoresDto.fechaDeNacimiento();
        this.fechaDeMuerte = autoresDto.fechaDeMuerte();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(String fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public Libros getLibros() {
        return libros;
    }

    public void setLibros(Libros libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return
                "Nombre del Autor: " + nombreAutor + " "+
                "Fecha de Nacimiento: '" + fechaDeNacimiento + " " +
                "Fecha de Muerte: " + fechaDeMuerte + " \n";
    }
}
