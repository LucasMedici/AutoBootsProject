package com.autobots.automanager;

import java.util.Calendar;
import java.util.Date;

import com.autobots.automanager.entities.*;
import com.autobots.automanager.enums.UserRoles;
import com.autobots.automanager.enums.DocumentsTypes;
import com.autobots.automanager.enums.VehicleTypes;
import com.autobots.automanager.repositories.ClientRepository;
import com.autobots.automanager.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class AutomanagerApplication{



	public static void main(String[] args) {
		SpringApplication.run(AutomanagerApplication.class, args);
	}


	@Component
	public static class Runner implements ApplicationRunner {

		@Autowired
		private CompanyRepository companyRepository;

		@Autowired
		public ClientRepository repository;

		@Override
		public void run(ApplicationArguments args) throws Exception {

			Calendar calendario = Calendar.getInstance();
			calendario.set(2004, 03, 12);

			Client cliente = new Client();
			cliente.setNome("Lucas Medici");
			cliente.setDataCadastro(Calendar.getInstance().getTime());
			cliente.setDataNascimento(calendario.getTime());
			cliente.setNomeSocial("Lucas");

			Telephone telefone = new Telephone();
			telefone.setDdd("11");
			telefone.setNumero("989593219");
			cliente.getTelephones().add(telefone);

			Adress endereco = new Adress();
			endereco.setEstado("São Paulo");
			endereco.setCidade("São José dos Campos");
			endereco.setBairro("Bosque");
			endereco.setRua("Manoel Fiel Filho");
			endereco.setNumero("113");
			endereco.setCodigoPostal("12233690");
			endereco.setInformacoesAdicionais("Próximo ao rosalina");
			cliente.setAdress(endereco);

			Document rg = new Document();
			rg.setTipo(String.valueOf(DocumentsTypes.RG));
			rg.setNumero("393634115");

			Document cpf = new Document();
			cpf.setTipo(String.valueOf(DocumentsTypes.CPF));
			cpf.setNumero("52287510842");

			cliente.getDocuments().add(rg);
			cliente.getDocuments().add(cpf);

			repository.save(cliente);

			Company company = new Company();
			company.setRazaoSocial("Manutenção das boas");
			company.setNomeFantasia("Serviço Manutenção Veicular");
			company.setCadastro(new Date());

			Adress enderecoEmpresa = new Adress();
			enderecoEmpresa.setEstado("São Paulo");
			enderecoEmpresa.setCidade("São Paulo");
			enderecoEmpresa.setBairro("Paulista");
			enderecoEmpresa.setRua("Av. Paulista");
			enderecoEmpresa.setNumero("10001");
			enderecoEmpresa.setCodigoPostal("04049050");

			company.setEndereco(enderecoEmpresa);

			Telephone telefoneEmpresa = new Telephone();
			telefoneEmpresa.setDdd("011");
			telefoneEmpresa.setNumero("952321819");

			company.getTelefones().add(telefoneEmpresa);

			User funcionario = new User();
			funcionario.setNome("Bruno");
			funcionario.setNomeSocial("Bruno");
			funcionario.getPerfis().add(UserRoles.FUNCIONARIO);

			Email emailFuncionario = new Email();
			emailFuncionario.setEndereco("bruno@gmail.com");

			funcionario.getEmails().add(emailFuncionario);

			Adress enderecoFuncionario = new Adress();
			enderecoFuncionario.setEstado("São Paulo");
			enderecoFuncionario.setCidade("São Paulo");
			enderecoFuncionario.setBairro("Praça da Árvoer");
			enderecoFuncionario.setRua("Rua dos Jacintos");
			enderecoFuncionario.setNumero("372");
			enderecoFuncionario.setCodigoPostal("04049050");

			funcionario.setEndereco(enderecoFuncionario);

			company.getUsers().add(funcionario);

			Telephone telefoneFuncionario = new Telephone();
			telefoneFuncionario.setDdd("11");
			telefoneFuncionario.setNumero("958493210");

			funcionario.getTelefones().add(telefoneFuncionario);

			Document cpf2 = new Document();
			cpf2.setNumero("544938291");
			cpf2.setTipo(String.valueOf(DocumentsTypes.CPF));

			funcionario.getDocumentos().add(cpf2);

			CredentialUserPassword credencialFuncionario = new CredentialUserPassword();
			credencialFuncionario.setAtividade(false);
			credencialFuncionario.setNomeUsuario("bruno");
			credencialFuncionario.setSenha("123");
			credencialFuncionario.setCriadoEm(new Date());
			credencialFuncionario.setUltimoAcesso(new Date());

			funcionario.getCredenciais().add(credencialFuncionario);

			User fornecedor = new User();
			fornecedor.setNome("Vendas Carros LTDA");
			fornecedor.setNomeSocial("Venda de Peças de carros");
			fornecedor.getPerfis().add(UserRoles.FORNECEDOR);

			Email emailFornecedor = new Email();
			emailFornecedor.setEndereco("forncedor@gmail.com");

			fornecedor.getEmails().add(emailFornecedor);

			CredentialUserPassword credencialFornecedor = new CredentialUserPassword();
			credencialFornecedor.setAtividade(false);
			credencialFornecedor.setNomeUsuario("fornecedor");
			credencialFornecedor.setSenha("123");
			credencialFornecedor.setCriadoEm(new Date());
			credencialFornecedor.setUltimoAcesso(new Date());

			fornecedor.getCredenciais().add(credencialFornecedor);

			Document cnpj = new Document();
			cnpj.setNumero("12321312554");
			cnpj.setTipo(String.valueOf(DocumentsTypes.CPF));

			fornecedor.getDocumentos().add(cnpj);

			Adress enderecoFornecedor = new Adress();
			enderecoFornecedor.setEstado("Minas Gerais");
			enderecoFornecedor.setCidade("Minas Gerais");
			enderecoFornecedor.setBairro("Centro");
			enderecoFornecedor.setRua("Av. do Pão e Queijo");
			enderecoFornecedor.setNumero("20");
			enderecoFornecedor.setCodigoPostal("59011589");

			fornecedor.setEndereco(enderecoFornecedor);

			company.getUsers().add(fornecedor);

			Products rodaLigaLeve = new Products();
			rodaLigaLeve.setCadastro(new Date());
			rodaLigaLeve.setFabricao(new Date());
			rodaLigaLeve.setNome("Roda de liga leve");
			rodaLigaLeve.setValidade(new Date());
			rodaLigaLeve.setQuantidade(30);
			rodaLigaLeve.setValor(300.0);
			rodaLigaLeve.setDescricao("Roda de liga leve de alta qualidade");

			company.getProducts().add(rodaLigaLeve);

			fornecedor.getProducts().add(rodaLigaLeve);

			User cliente2 = new User();
			cliente2.setNome("Andre da Silva");
			cliente2.setNomeSocial("Andre");
			cliente2.getPerfis().add(UserRoles.CLIENTE);

			Email emailCliente = new Email();
			emailCliente.setEndereco("andre@gmail.com");

			cliente2.getEmails().add(emailCliente);

			Document cpfCliente = new Document();
			cpfCliente.setNumero("745646545");
			cpfCliente.setTipo(String.valueOf(DocumentsTypes.CPF));

			cliente2.getDocumentos().add(cpfCliente);

			CredentialUserPassword credencialCliente = new CredentialUserPassword();
			credencialCliente.setAtividade(false);
			credencialCliente.setNomeUsuario("andre");
			credencialCliente.setSenha("321");
			credencialCliente.setCriadoEm(new Date());
			credencialCliente.setUltimoAcesso(new Date());

			cliente2.getCredenciais().add(credencialCliente);

			Adress enderecoCliente = new Adress();
			enderecoCliente.setEstado("São Paulo");
			enderecoCliente.setCidade("São José dos Campos");
			enderecoCliente.setBairro("Bosque");
			enderecoCliente.setRua("Rua wladimir herzog");
			enderecoCliente.setNumero("30");
			enderecoCliente.setCodigoPostal("1223-030");

			cliente2.setEndereco(enderecoCliente);

			Vehicle vehicle = new Vehicle();
			vehicle.setPlaca("AAA-1111");
			vehicle.setModelo("Bulgatti");
			vehicle.setTipo(VehicleTypes.SPORT);
			vehicle.setProprietario(cliente2);

			cliente2.getVehicles().add(vehicle);

			company.getUsers().add(cliente2);

			Services trocaVidros = new Services();
			trocaVidros.setDescricao("Serviço de troca de janelas do veículo");
			trocaVidros.setNome("Troca de janelas");
			trocaVidros.setValor(10000);

			Services pintura = new Services();
			pintura.setDescricao("Pintura do carro em diferentes cores");
			pintura.setNome("Pintura do Carro");
			pintura.setValor(500);

			company.getServices().add(trocaVidros);
			company.getServices().add(pintura);

			Sell sell = new Sell();
			sell.setCadastro(new Date());
			sell.setCliente(cliente2);
			sell.getProducts().add(rodaLigaLeve);
			sell.setIdentificacao("123");
			sell.setFuncionario(funcionario);
			sell.getServices().add(trocaVidros);
			sell.getServices().add(pintura);
			sell.setVehicle(vehicle);
			vehicle.getSells().add(sell);

			company.getSells().add(sell);

			companyRepository.save(company);

			Products rodaLigaLeve2 = new Products();
			rodaLigaLeve2.setCadastro(new Date());
			rodaLigaLeve2.setFabricao(new Date());
			rodaLigaLeve2.setNome("Roda de liga Leve");
			rodaLigaLeve2.setValidade(new Date());
			rodaLigaLeve2.setQuantidade(50);
			rodaLigaLeve2.setValor(100.0);
			rodaLigaLeve2.setDescricao("Roda de liga leve original");

			Services trocaVidros2 = new Services();
			trocaVidros2.setDescricao("Troca de janelas docarro");
			trocaVidros2.setNome("Troca de janelas");
			trocaVidros2.setValor(50);

			Services pintura2 = new Services();
			pintura2.setDescricao("pintura do carro ");
			pintura2.setNome("Pintura");
			pintura2.setValor(50);

			Sell sell2 = new Sell();
			sell2.setCadastro(new Date());
			sell2.setCliente(cliente2);
			sell2.getProducts().add(rodaLigaLeve2);
			sell2.setIdentificacao("123");
			sell2.setFuncionario(funcionario);
			sell2.getServices().add(pintura2);
			sell2.getServices().add(trocaVidros2);
			sell2.setVehicle(vehicle);
			vehicle.getSells().add(sell2);

			company.getSells().add(sell2);

			companyRepository.save(company);

		}
	}

}