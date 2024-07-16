package com.valhalla.challenge_literalura.service;

import com.valhalla.challenge_literalura.dto.JsonResponse;
import com.valhalla.challenge_literalura.dto.LibrosDto;
import com.valhalla.challenge_literalura.model.Libros;
import com.valhalla.challenge_literalura.respository.AutoresRepository;
import com.valhalla.challenge_literalura.respository.LibrosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GestionarLibro {

    private String URL_BASE = "https://gutendex.com/books/";

    private Scanner inputText = new Scanner(System.in);

    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private ConvertData convertData = new ConvertData();

    private LibrosRepository librosRepository;
    private AutoresRepository autoresRepository;

    public GestionarLibro(LibrosRepository librosRepository, AutoresRepository autoresRepository){
        this.librosRepository = librosRepository;
        this.autoresRepository = autoresRepository;
    }


    public void getDatosLibros(){

        do{

            System.out.print("Por favor, escribir el titulo del libro que estas buscando: ");
            String nombreLibro = inputText.nextLine();

            var jsonResponse = consumeAPI.obtenerDatosPeticion(URL_BASE+"?search="+nombreLibro.replace(" ", "+"));
            JsonResponse datos = convertData.obtenerDatos(jsonResponse, JsonResponse.class);
            var libro = datos.libros().stream()
                    .filter( l -> l.nombreLibro().toLowerCase().contains(nombreLibro.toLowerCase()))
                    .findFirst();

            if(libro.isPresent()){
                var libroEncontrado = libro.get();
                System.out.println("Libro encontrado!");
                System.out.println("\nFicha Tecnica del Libro");
                System.out.println("Titulo: "+libroEncontrado.nombreLibro());
                System.out.println("Idioma: "+libroEncontrado.idiomas());
                System.out.println("Descargas: "+libroEncontrado.cantidadDescargas());
                System.out.println();
                guardarLibro(libroEncontrado);
                break;
            }else{
                System.out.println("\nNo se pudo encontrar el libro: " +nombreLibro+", intente con otro.");
            }

        }while (true);

    }

    public void getLibrosRegistrados(){

        var result = librosRepository.findAll();

        System.out.println("\nLos libros registrados son: ");
        result.stream().
                forEach( l ->
                        System.out.print(l));

    }

    public void getAutoresRegistrados(){

        var result = autoresRepository.findAll();

        System.out.println("\nLos Autores registrados son: ");
        result.stream().
                forEach( l ->
                        System.out.print(l));

    }

    public void getAutoresPorFecha(){

        System.out.println("Por favor, escribir el año en el que desea buscar: ");
        String year = inputText.nextLine();

        var result = autoresRepository.findAutoresVivosEnAnio(year);

        if(result.isEmpty()){
            System.out.println("No se encontraron autores vivos para esa fecha");
        }else{
            System.out.println("Autores vivos en el año: "+ year);
            result.stream().forEach(System.out::println);
        }

    }

    public void getLibrosPorIdiomas(){

        String idioma = null;

        System.out.print("Por favor, seleccione un idioma: \n1. Español \n2. Ingles \n3. Italiano \n4. Frances \nOpcion: ");
        var optionIdioma = inputText.nextInt();
        inputText.nextLine();

        switch (optionIdioma){
            case 1:
                idioma = "es";
                break;
            case 2:
                idioma = "en";
                break;
            case 4:
                idioma = "it";
                break;
            case 5:
                idioma = "fr";
                break;

        }

        var resul = librosRepository.findByIdiomasContainsIgnoreCase(idioma);

        if(resul.isEmpty()){
            System.out.println("\nNo se encontraron libros en el idioma: "+idioma);
        }else{

            DoubleSummaryStatistics estadisticas = resul.stream()
                    .filter( l -> l.getCantidadDescargas() > 0)
                    .collect(Collectors.summarizingDouble(Libros::getCantidadDescargas));

            System.out.println("\nLos libros escritos en el idioma "+idioma+" son: ");
            resul.stream()
                    .forEach(System.out::print);

            System.out.println("Cantidad de libros: "+estadisticas.getCount());
            System.out.println("Libro con mayor descargas "+estadisticas.getMax());
        }


    }

    private void guardarLibro(LibrosDto librosDto){

        try{
            Libros libros = new Libros(librosDto);
            libros.setAutores(librosDto.autores());
            librosRepository.save(libros);

            System.out.println("Libro guardado correctamente!");

        }catch (DataIntegrityViolationException e){

            System.out.println("No se puede registrar, ya existe en la base de datos!");
        }

    }



}
