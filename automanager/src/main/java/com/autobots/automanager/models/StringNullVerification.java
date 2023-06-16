package com.autobots.automanager.models;

public class StringNullVerification {

	public boolean verify(String data) {
		boolean nulll = true;
		if (!(data == null)) {
			if (!data.isBlank()) {
				nulll = false;
			}
		}
		return nulll;
	}
}