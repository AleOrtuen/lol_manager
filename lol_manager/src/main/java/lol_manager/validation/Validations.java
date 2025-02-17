package lol_manager.validation;

import lol_manager.model.Champion;
import lol_manager.model.User;

public class Validations {

	private static final String EMAIL_REGEX = "^([A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
	private static final String PASSWORD_REGEX = "^(?=(.*[A-Z]))(?=(.*[a-z]))(?=(.*[0-9]))[A-Za-z0-9]{8,}$";   
	
	// VALIDAZIONE UTENTE
	public static boolean validFormUtente(User u) {
		boolean valid =
				u.getEmail() != null 
			 && validEmail(u.getEmail())
			 && u.getUsername() != null
			 &&	validUsername(u.getUsername())
			 && u.getPassword() != null
			 && validPassword(u.getPassword());
		return valid;
	}
	
	public static boolean validUsername(String username) {
		boolean valid =
				username.length() >= 3
			 && username.length() <= 50;
		return valid;
	}
	
	public static boolean validEmail(String email) {
		boolean valid =
				email.matches(EMAIL_REGEX)
			 && email.length() <= 50;
		return valid;
	}
	
	public static boolean validPassword(String password) {
		boolean valid =
				password.matches(PASSWORD_REGEX)
			 && password.length() <= 100;
		return valid;
	}
	
	// VALIDAZIONE CHAMPIONS
	public static boolean validChamp(Champion c) {
		boolean valid = 
				c.getName() != null
			&&	c.getImg() != null;
		return valid;
	}
}
