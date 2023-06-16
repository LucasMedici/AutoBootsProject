package com.autobots.automanager.models;

import java.util.List;

import com.autobots.automanager.entities.Document;

public class DocumentUpdate {
	private StringNullVerification verify = new StringNullVerification();

	public void update(Document document, Document data) {
		if (data != null) {
			if (!verify.verify(data.getTipo())) {
				document.setTipo(data.getTipo());
			}
			if (!verify.verify(data.getNumero())) {
				document.setNumero(data.getNumero());
			}
		}
	}

	public void update(List<Document> documents, List<Document> datas) {
		for (Document data : datas) {
			for (Document document : documents) {
				if (data.getId() != null) {
					if (data.getId() == document.getId()) {
						update(document, data);
					}
				}
			}
		}
	}
}
