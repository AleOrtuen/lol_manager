package lol_manager.model.embedded;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ChampRoleEmbedded implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id_champ")
	private Long idChamp;
	
	@Column(name = "id_comp")
	private Long idComp;
	
	@Column(name = "role")
	private String role;
	
	public ChampRoleEmbedded() {
		
	}

	public ChampRoleEmbedded(Long idChamp, Long idComp, String role) {
		this.idChamp = idChamp;
		this.idComp = idComp;
		this.role = role;
	}

	public Long getIdChamp() {
		return idChamp;
	}

	public void setIdChamp(Long idChamp) {
		this.idChamp = idChamp;
	}

	public Long getIdComp() {
		return idComp;
	}

	public void setIdComp(Long idComp) {
		this.idComp = idComp;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idChamp, idComp, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChampRoleEmbedded other = (ChampRoleEmbedded) obj;
		return Objects.equals(idChamp, other.idChamp) && Objects.equals(idComp, other.idComp)
				&& Objects.equals(role, other.role);
	}


	
}
