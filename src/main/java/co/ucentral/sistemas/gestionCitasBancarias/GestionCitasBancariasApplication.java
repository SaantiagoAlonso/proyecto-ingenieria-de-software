package co.ucentral.sistemas.gestionCitasBancarias;

import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionCitasBancariasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionCitasBancariasApplication.class, args);
		System.out.println("Proyecto citas");
	}
	@Autowired
	RepoCliente respositorioCliente;

}
