package com.autobots.automanager.models;

import com.autobots.automanager.entities.Adress;

public class AdressUpdate {
	private StringNullVerification verify = new StringNullVerification();

	public void update(Adress adress, Adress update) {
		if (update != null) {
			if (!verify.verify(update.getEstado())) {
				adress.setEstado(update.getEstado());
			}


			if (!verify.verify(update.getCidade())) {
				adress.setCidade(update.getCidade());
			}


			if (!verify.verify(update.getBairro())) {
				adress.setBairro(update.getBairro());
			}


			if (!verify.verify(update.getRua())) {
				adress.setRua(update.getRua());
			}


			if (!verify.verify(update.getNumero())) {
				adress.setNumero(update.getNumero());
			}


			if (!verify.verify(update.getInformacoesAdicionais())) {
				adress.setInformacoesAdicionais(update.getInformacoesAdicionais());
			}
		}
	}
}