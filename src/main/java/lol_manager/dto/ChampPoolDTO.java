package lol_manager.dto;

public class ChampPoolDTO {

	private UserDTO user;
	private ChampDTO champion;
	private Long idUser;
	private Long idChamp;
	
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public ChampDTO getChampion() {
		return champion;
	}
	public void setChampion(ChampDTO champion) {
		this.champion = champion;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Long getIdChamp() {
		return idChamp;
	}
	public void setIdChamp(Long idChamp) {
		this.idChamp = idChamp;
	}
	
	
}
