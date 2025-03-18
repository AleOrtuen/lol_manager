package lol_manager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lol_manager.model.embedded.TeamMemberEmbedded;

@Entity
@Table(name = "team_members")
public class TeamMember {

	@EmbeddedId
	private TeamMemberEmbedded idTeamMember = new TeamMemberEmbedded();
	
	@ManyToOne
	@JsonBackReference(value = "user-teammember-reference")
	@MapsId("idUser")
	@JoinColumn(name = "id_user", insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JsonBackReference(value = "team-teammember-reference")
	@MapsId("idTeam")
	@JoinColumn(name = "id_team", insertable = false, updatable = false)
	private Team team;
	
	public TeamMember() {
		
	}

	public TeamMember(User user, Team team) {
		this.idTeamMember = new TeamMemberEmbedded(user.getIdUser(), team.getIdTeam());
		this.user = user;
		this.team = team;
	}

	public TeamMemberEmbedded getIdTeamMember() {
		return idTeamMember;
	}

	public void setIdTeamMember(TeamMemberEmbedded idTeamMember) {
		this.idTeamMember = idTeamMember;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	
}
