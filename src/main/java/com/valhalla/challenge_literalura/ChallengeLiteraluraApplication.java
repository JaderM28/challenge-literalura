package com.valhalla.challenge_literalura;

import com.valhalla.challenge_literalura.respository.AutoresRepository;
import com.valhalla.challenge_literalura.respository.LibrosRepository;
import com.valhalla.challenge_literalura.view.MenuPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibrosRepository librosRepository;

	@Autowired
	private AutoresRepository autoresRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		MenuPrincipal menuPrincipal = new MenuPrincipal(librosRepository, autoresRepository);
		menuPrincipal.mostrarMenuBiblioteca();

	}
}
