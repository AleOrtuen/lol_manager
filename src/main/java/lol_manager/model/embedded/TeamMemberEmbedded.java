package lol_manager.model.embedded;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TeamMemberEmbedded implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_user")
	private Long idUser;
	
	@Column(name = "id_team")
	private Long idTeam;

	public TeamMemberEmbedded() {
		
	}
	
	public TeamMemberEmbedded(Long idUser, Long idTeam) {
		this.idUser = idUser;
		this.idTeam = idTeam;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Long idTeam) {
		this.idTeam = idTeam;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTeam, idUser);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamMemberEmbedded other = (TeamMemberEmbedded) obj;
		return Objects.equals(idTeam, other.idTeam) && Objects.equals(idUser, other.idUser);
	}

	
	
}
