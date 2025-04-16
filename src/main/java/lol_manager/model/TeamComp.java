package lol_manager.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "team_comps")
public class TeamComp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comp")
	private Long idComp;
	
	@ManyToOne
	@JoinColumn(name = "id_team", nullable = false)
	private Team team;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "descr")
	private String descr;
	
    @OneToMany(mappedBy = "idChampRole.idComp", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "champrole-teamcomp-reference")
    private List<ChampRole> champRoles;
	
	public TeamComp() {
		
	}

	public TeamComp(Team team, String name, String descr) {
		this.team = team;
		this.name = name;
		this.descr = descr;
	}

	public Long getIdComp() {
		return idComp;
	}

	public void setIdComp(Long idComp) {
		this.idComp = idComp;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public List<ChampRole> getChampRoles() {
		return champRoles;
	}

	public void setChampRoles(List<ChampRole> champRoles) {
		this.champRoles = champRoles;
	}
	
	
}
