package lol_manager.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "admin")
	private boolean admin;
	
	@Column(name = "p_role")
	private String pRole;
	
	@OneToMany(mappedBy = "idChampPool.idUser")
    @JsonManagedReference(value = "user-champpool-reference")
    private List<ChampPool> champPools; 
    
    @OneToMany(mappedBy = "idTeamMember.idUser")
    @JsonManagedReference(value = "user-teammember-reference")
    private List<TeamMember> teamMembers;
    
	public User () {
		
	}

	public User(String username, String email, String password, String pRole) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.pRole = pRole;
	}
	
	public List<Champion> getChampions() {
		List<Champion> champions = new ArrayList<>();
		if(champPools != null) {
			getChampPools().forEach(el->champions.add(el.getChamp()));
		}
		return champions;
	}

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

    public String getpRole() {
		return pRole;
	}

	public void setpRole(String pRole) {
		this.pRole = pRole;
	}
	
	public List<ChampPool> getChampPools() {
		return champPools;
	}

	public void setChampPools(List<ChampPool> champPools) {
		this.champPools = champPools;
	}

	public List<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

	
	
}
