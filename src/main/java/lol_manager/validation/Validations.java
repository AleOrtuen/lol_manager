package lol_manager.validation;

import lol_manager.dto.*;
import lol_manager.enums.ChampRoleEnum;
import lol_manager.enums.GameEnum;
import lol_manager.enums.SideSelectionEnum;

public class Validations {

	private static final String EMAIL_REGEX = "^([A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
	private static final String PASSWORD_REGEX = "^(?=(.*[A-Z]))(?=(.*[a-z]))(?=(.*[0-9]))[A-Za-z0-9]{8,}$";   
	
	// VALIDAZIONE UTENTE
	public static boolean isValidFormUser(UserDTO user) {
		boolean valid =
				user.getEmail() != null 
			 && isValidEmail(user.getEmail())
			 && user.getUsername() != null
			 &&	isValidUsername(user.getUsername())
			 && user.getPassword() != null
			 && isValidPassword(user.getPassword());
		return valid;
	}
	
	public static boolean isValidFormNoPassword(UserDTO user) {
		boolean valid =
				user.getEmail() != null 
			 && isValidEmail(user.getEmail())
			 && user.getUsername() != null
			 &&	isValidUsername(user.getUsername());
		return valid;
	}
	
	public static boolean isValidUsername(String username) {
		boolean valid =
				username.length() >= 3
			 && username.length() <= 50;
		return valid;
	}
	
	public static boolean isValidEmail(String email) {
		boolean valid =
				email.matches(EMAIL_REGEX)
			 && email.length() <= 50;
		return valid;
	}
	
	public static boolean isValidPassword(String password) {
		boolean valid =
				password.matches(PASSWORD_REGEX)
			 && password.length() <= 100;
		return valid;
	}
	
	// VALIDAZIONE CHAMPIONS
	public static boolean isValidChamp(ChampDTO c) {
		boolean valid = 
				c.getName() != null
			&&	c.getImg() != null;
		return valid;
	}
	
	// VALIDAZIONE CHAMP_POOL
	public static boolean isValidChampPool(ChampPoolDTO c) {
		boolean valid =
				c.getUser().getIdUser() != null
			&&  c.getChampion().getIdChamp() != null;
		return valid;
	}
	
	//VALIDAZIONE TEAM_MEMBER
	public static boolean isValidTeamMember(TeamMemberDTO t) {
		boolean valid =
				t.getUser().getIdUser() != null
			&&	t.getTeam().getIdTeam() != null;
		return valid;
	}
	
	// VALIDAZIONE TEAM
	public static boolean isValidTeam(TeamDTO t) {
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
	public static boolean isValidTeamComp(TeamCompDTO t) {
		boolean valid = 
				t.getTeam() != null
			&&	t.getTeam().getIdTeam() != null
			&&	t.getName() != null
			&&	t.getName().length() <= 50
			&&	t.getDescr().length() <= 255;
		return valid;
	}
	
	// VALIDAZIONE CHAMP ROLE
	public static boolean isValidChampRole(ChampRoleDTO c) {
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
	
	// VALIDAZIONE GAME
	public static boolean isValidGame(GameDTO gameDto) {
		boolean valid = false;
		
		try {
			GameEnum style = GameEnum.valueOf(gameDto.getStyle());
			valid =
					style != null;
			
		} catch (IllegalArgumentException e) {
			valid = false;
		}
		return valid;
	}
	
	// VALIDAZIONE DRAFT
	public static boolean isValidDraft(DraftDTO draftDto) {
		boolean valid = false;
		
		valid = 
				draftDto.getGame().getIdGame() != null;
//						&& draftDto.getTeamBlue().getIdTeam() != null &&
//				draftDto.getTeamRed().getIdTeam() != null;
		return valid;
	}
	
	// VALIDAZIONE BAN
	public static boolean isValidBan(BanDTO banDto) {
		boolean valid = false;
		
		try {
			SideSelectionEnum side = SideSelectionEnum.valueOf(banDto.getSide());
			valid = 
					banDto.getDraft().getIdDraft() != null &&
					side != null &&
					banDto.getBan().getIdChamp() != null
					;
		} catch (IllegalArgumentException e) {
			valid = false;
		}
		return valid;
	}
	
	// VALIDAZIONE PICK
	public static boolean isValidPick(PickDTO pickDto) {
		boolean valid = false;
		
		try {
			SideSelectionEnum side = SideSelectionEnum.valueOf(pickDto.getSide());
			valid = 
					pickDto.getDraft().getIdDraft() != null
					&& side != null
					&& pickDto.getPick().getIdChamp() != null;
		} catch (IllegalArgumentException e) {
			valid = false;
		}
		return valid;
	}

	// VALIDAZIONE LEAGUE ROLE
	public static boolean isValidLeagueRole(LeagueRoleDTO leagueRoleDto) {
		boolean valid = false;

		try {
			ChampRoleEnum role = ChampRoleEnum.valueOf(leagueRoleDto.getRole());
			valid = leagueRoleDto.getChamp().getIdChamp() != null && role != null;
		} catch (IllegalArgumentException e) {
			valid = false;
		}
		return valid;
	}
}
