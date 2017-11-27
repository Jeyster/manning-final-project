package com.sopra;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	public boolean checkStringsPresence(List<String> listString, String sentence) {

		for (int i = 0; i < listString.size(); i++) {

			if (sentence.contains(listString.get(i))) { // Si la sentence
														// contient un élément
														// du tableau

				return true;
			}

		}
		return false;
	}
	
	public String convertLoginWithSpaces(String login){
		return login.replace(' ', '-');
	}

	public boolean loginValidation(String sentence) {
		
		Pattern letter = Pattern.compile("[^a-zA-Z0-9]+");
		Pattern special = Pattern.compile("[.-_]+[^@]");

		Matcher hasLetter = letter.matcher(sentence);
		Matcher hasSpecial = special.matcher(sentence);
		boolean b = hasLetter.find();
		boolean c = hasSpecial.find();
		

		if (b == true || c == true ) {
			return true;
		}

		return false;

	}

}
