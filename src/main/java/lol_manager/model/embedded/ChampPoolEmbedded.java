package lol_manager.model.embedded;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ChampPoolEmbedded implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id_user")
	private Long idUser;
	
	@Column(name = "id_champ")
	private Long idChamp;
	
	public ChampPoolEmbedded() {
		
	}

	public ChampPoolEmbedded(Long idUser, Long idChamp) {
		this.idUser = idUser;
		this.idChamp = idChamp;
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

	@Override
	public int hashCode() {
		return Objects.hash(idChamp, idUser);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChampPoolEmbedded other = (ChampPoolEmbedded) obj;
		return Objects.equals(idChamp, other.idChamp) && Objects.equals(idUser, other.idUser);
	}
	
	
	
}
