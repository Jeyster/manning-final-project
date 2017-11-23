package com.sopra;

import java.util.Arrays;
import java.util.List;

public class Constants {


	

	//OBJECTS
	public static List<String> listChar = Arrays.asList(" ", "%", "é", "'", "+",".","@", "*");
	
	//PAGES
	public static String HOME_PAGE = "home.html";
	public static String EDITION_PAGE="edition.html";
	public static String LOGIN_PAGE="login";
	public static String REGISTER_PAGE="register";

	//ALERTS
	public static String NOT_SAME_PASSWORD_ALERT = "Error: password not confirmed";
	public static String BAD_PASSWORD_OR_LOGIN_ALERT = "Error: bad password or login";
	public static String LOGIN_ALREADY_USED_ALERT = "Error: login is already used";
	public static String EMPTY_FIELD_ALERT = "Error: login and password can't be empty";
	public static String EMAIL_IS_NOT_VALID = "Error, you must enter an valid email";
	public static String EMAIL_ALREADY_USED_ALERT = "Error: login is already used";
	public static String RESET_PASSWORD = "";

	public static String LOGIN_IS_NOT_CORRECT = "Error: login not correct, do not use space " + String.join(" , ", listChar );
	;
	//ATTRIBUTES	
	public static String ALERT_ATTRIBUTE="alert";
	public static String CONNECTED_USER_ATTRIBUTE="connectedUser";
	
	
}

