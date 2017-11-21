package com.sopra;

import java.util.List;

public class Tools {

	public boolean checkStringsPresence(List<String> listString, String sentence) {

		for (int i = 0; i < listString.size(); i++) {

			if (sentence.contains(listString.get(i))) { // Si la sentence contient un élément du tableau

				return true;
			}

		}
		return false;
	}
	

}
