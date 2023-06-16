package com.autobots.automanager.models;

import java.util.List;

import com.autobots.automanager.entities.Telephone;

public class TelephoneUpdate {
	private StringNullVerification verify = new StringNullVerification();

	public void update(Telephone telephone, Telephone data) {
		if (data != null) {
			if (!verify.verify(data.getDdd())) {
				telephone.setDdd(data.getDdd());
			}
			if (!verify.verify(data.getNumero())) {
				telephone.setNumero(data.getNumero());
			}
		}
	}

	public void update(List<Telephone> telephones, List<Telephone> datas) {
		for (Telephone data : datas) {
			for (Telephone telephone : telephones) {
				if (data.getId() != null) {
					if (data.getId() == telephone.getId()) {
						update(telephone, data);
					}
				}
			}
		}
	}
}