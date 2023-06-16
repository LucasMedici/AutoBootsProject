package com.autobots.automanager;

import java.util.Calendar;

import com.autobots.automanager.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Document;
import com.autobots.automanager.entities.Adress;
import com.autobots.automanager.entities.Telephone;
import com.autobots.automanager.repositories.ClientRepository;

@SpringBootApplication
public class AutomanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomanagerApplication.class, args);
	}

	@Component
	public static class Runner implements ApplicationRunner {
		@Autowired
		public ClientRepository repositorio;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			Calendar calendario = Calendar.getInstance();
			calendario.set(2004, 03, 12);

			Client client = new Client();
			client.setNome("Lucas");
			client.setDataCadastro(Calendar.getInstance().getTime());
			client.setDataNascimento(calendario.getTime());
			client.setNomeSocial("Lucas");
			
			Telephone telephone = new Telephone();
			telephone.setDdd("11");
			telephone.setNumero("934723219");
			client.getTelephones().add(telephone);
			
			Adress adress = new Adress();
			adress.setEstado("São Paulo");
			adress.setCidade("São José dos Campos");
			adress.setBairro("Bosque");
			adress.setRua("Manoel fiel Filho");
			adress.setNumero("113");
			adress.setCodigoPostal("12233690");
			adress.setInformacoesAdicionais("Próximo ao mercado");
			client.setAdress(adress);
			
			Document rg = new Document();
			rg.setTipo("RG");
			rg.setNumero("39363");
			
			Document cpf = new Document();
			cpf.setTipo("CPF");
			cpf.setNumero("52287510842");
			
			client.getDocuments().add(rg);
			client.getDocuments().add(cpf);
			
			repositorio.save(client);
		}
	}

}
