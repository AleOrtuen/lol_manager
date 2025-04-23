package lol_manager.validation;

import lol_manager.dto.ChampDTO;
import lol_manager.dto.ChampPoolDTO;
import lol_manager.dto.ChampRoleDTO;
import lol_manager.dto.TeamCompDTO;
import lol_manager.dto.TeamDTO;
import lol_manager.dto.TeamMemberDTO;
import lol_manager.dto.UserDTO;
import lol_manager.enums.ChampRoleEnum;

public class Validations {

	private static final String EMAIL_REGEX = "^([A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
	private static final String PASSWORD_REGEX = "^(?=(.*[A-Z]))(?=(.*[a-z]))(?=(.*[0-9]))[A-Za-z0-9]{8,}$";   
	
	// VALIDAZIONE UTENTE
	public static boolean validFormUtente(UserDTO user) {
		boolean valid =
				user.getEmail() != null 
			 && validEmail(user.getEmail())
			 && user.getUsername() != null
			 &&	validUsername(user.getUsername())
			 && user.getPassword() != null
			 && validPassword(user.getPassword());
		return valid;
	}
	
	public static boolean validFormNoPassword(UserDTO user) {
		boolean valid =
				user.getEmail() != null 
			 && validEmail(user.getEmail())
			 && user.getUsername() != null
			 &&	validUsername(user.getUsername());
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
	public static boolean validChamp(ChampDTO c) {
		boolean valid = 
				c.getName() != null
			&&	c.getImg() != null;
		return valid;
	}
	
	// VALIDAZIONE CHAMP_POOL
	public static boolean validChampPool(ChampPoolDTO c) {
		boolean valid =
				c.getUser().getIdUser() != null
			&&  c.getChampion().getIdChamp() != null;
		return valid;
	}
	
	//VALIDAZIONE TEAM_MEMBER
	public static boolean validTeamMember(TeamMemberDTO t) {
		boolean valid =
				t.getUser().getIdUser() != null
			&&	t.getTeam().getIdTeam() != null;
		return valid;
	}
	
	// VALIDAZIONE TEAM
	public static boolean validTeam(TeamDTO t) {
		boolean valid =
				t.getName() != null
			&&  t.getName().length() >= 2
			&&  t.getName().length() <= 50
			&&  t.getTag() != null
			&&  t.getTag().length() >= 2
			&&  t.getTag().length() <= 5
//			&&  t.getImg() != null
//			&&  t.getImg().length() >= 5
//			&&  t.getImg().length() <= 50
			;
		return valid;
	}
	
	// VALIDAZIONE TEAM COMP
	public static boolean validTeamComp(TeamCompDTO t) {
		boolean valid = 
				t.getTeam() != null
			&&	t.getTeam().getIdTeam() != null
			&&	t.getName() != null
			&&	t.getName().length() <= 50
			&&	t.getDescr().length() <= 255;
		return valid;
	}
	
	// VALIDAZIONE CHAMP ROLE
	public static boolean validChampRole(ChampRoleDTO c) {
	    boolean valid = false;

	    try {
	        ChampRoleEnum role = ChampRoleEnum.valueOf(c.getRole()); 
	        valid = c.getChampion() != null
	        	&& c.getChampion().getIdChamp() != null
	        	&& c.getComp() != null
	            && c.getComp().getIdComp() != null
	            && role != null
	            && c.getDescr().length() <= 255; 
	    } catch (IllegalArgumentException e) {
	    	valid = false;
	    }

	    return valid;
	}
	
	
}
