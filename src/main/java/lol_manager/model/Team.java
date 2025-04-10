package lol_manager.model;

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
@Table(name = "teams")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_team")
	private Long idTeam;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "tag", nullable = false)
	private String tag;
	
	@Column(name = "img")
	private String img;

    @OneToMany(mappedBy = "idTeamMember.idTeam")
    @JsonManagedReference(value = "team-teammember-reference")
    private List<TeamMember> teamMembers;
	
    @OneToMany(mappedBy = "team") 
    private List<TeamComp> teamComps;
    
	public Team() {
		
	}
	
	public Team(String name, String tag, String img) {
		this.name = name;
		this.tag = tag;
		this.img = img;
	}

	public Long getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Long idTeam) {
		this.idTeam = idTeam;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public List<TeamComp> getTeamComps() {
		return teamComps;
	}

	public void setTeamComps(List<TeamComp> teamComps) {
		this.teamComps = teamComps;
	}
	
	
	
}
