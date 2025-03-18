package lol_manager.dto;

import java.util.List;

public class UserDTO {

	private Long idUser;
	private String username;
	private String email;
	private String password;
	private boolean admin;
	private List<ChampDTO> champions;
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<ChampDTO> getChampions() {
		return champions;
	}
	public void setChampions(List<ChampDTO> champions) {
		this.champions = champions;
	}

	
}
