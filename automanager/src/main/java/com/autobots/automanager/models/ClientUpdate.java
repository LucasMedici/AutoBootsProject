package com.autobots.automanager.models;

import com.autobots.automanager.entities.Client;

public class ClientUpdate {
	private StringNullVerification verify = new StringNullVerification();
	private AdressUpdate adressUpdate = new AdressUpdate();
	private DocumentUpdate documentUpdate = new DocumentUpdate();
	private TelephoneUpdate telephoneUpdate = new TelephoneUpdate();

	private void updateData(Client client, Client update) {
		if (!verify.verify(update.getNome())) {
			client.setNome(update.getNome());
		}
		if (!verify.verify(update.getNomeSocial())) {
			client.setNomeSocial(update.getNomeSocial());
		}
		if (!(update.getDataCadastro() == null)) {
			client.setDataCadastro(update.getDataCadastro());
		}
		if (!(update.getDataNascimento() == null)) {
			client.setDataNascimento(update.getDataNascimento());
		}
	}

	public void update(Client client, Client data_update) {
		updateData(client, data_update);
		adressUpdate.update(client.getAdress(), data_update.getAdress());
		documentUpdate.update(client.getDocuments(), data_update.getDocuments());
		telephoneUpdate.update(client.getTelephones(), data_update.getTelephones());
	}
}
