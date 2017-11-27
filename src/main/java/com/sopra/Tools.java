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
		
		Pattern letter = Pattern.compile("[a-zA-Z]+");
		Pattern digit = Pattern.compile("[0-9]+");
		Pattern special = Pattern.compile("[.-_]+[^@]");

		Matcher hasLetter = letter.matcher(sentence);
		Matcher hasDigit = digit.matcher(sentence);
		Matcher hasSpecial = special.matcher(sentence);
		boolean b = hasLetter.matches();
		boolean c = hasDigit.matches();
		boolean d = hasSpecial.matches();
		

		if (b == true || c == true || d == true) {
			return true;
		}

		return false;

	}
	
	public boolean isAFacebookUser(User user){
		if (user.getFacebookId()!=null){
			return true;
		}
		return false;
	}

}
