package com.valhalla.challenge_literalura.view;
import com.valhalla.challenge_literalura.respository.AutoresRepository;
import com.valhalla.challenge_literalura.respository.LibrosRepository;
import com.valhalla.challenge_literalura.service.GestionarLibro;


import java.util.Scanner;

public class MenuPrincipal {

    private Scanner inputText = new Scanner(System.in);
    private GestionarLibro gestionarLibro;

    public MenuPrincipal(LibrosRepository librosRepository, AutoresRepository autoresRepository){
        gestionarLibro = new GestionarLibro(librosRepository, autoresRepository);
    }

    public void mostrarMenuBiblioteca(){

        boolean bandera = true;

        do{

            System.out.println("\n\t\tMENU PRINCIPAL - LIBRERIA ALURA\n");
            System.out.println("""
                    1 - Buscar libros por titulo.
                    2 - Listar libros registrados.
                    3 - listar autores registrados.
                    4 - listar autores vivos en un determinado año.
                    5 - listar libros por idioma.
                    0 - salir del programa.
                    """);
            System.out.print("Opcion: ");
            int option = inputText.nextInt();
            inputText.nextLine();

            switch (option){
                case 1:
                    gestionarLibro.getDatosLibros();
                    break;
                case 2:
                    gestionarLibro.getLibrosRegistrados();
                    break;
                case 3:
                    gestionarLibro.getAutoresRegistrados();
                    break;
                case 4:
                    gestionarLibro.getAutoresPorFecha();
                    break;
                case 5:
                    gestionarLibro.getLibrosPorIdiomas();
                    break;
                case 0:
                    System.out.println("Gracias por usar el sistema!");
                    bandera = false;
                    break;
                default:
                    System.out.println("Opcion no valida, intente nuevamente!");
                    break;

            }

        }while (bandera);

    }


}
