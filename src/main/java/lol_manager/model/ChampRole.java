package lol_manager.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lol_manager.model.embedded.ChampRoleEmbedded;

@Entity
@Table(name = "champ_role")
public class ChampRole {

	@EmbeddedId
	private ChampRoleEmbedded idChampRole = new ChampRoleEmbedded();
	
    @ManyToOne
    @JsonBackReference(value = "champrole-teamcomp-reference")
    @MapsId("idComp")
    @JoinColumn(name = "id_comp", insertable = false, updatable = false)
	private TeamComp comp;
	
    @ManyToOne
    @JsonBackReference(value = "champrole-champ-reference")
    @MapsId("idChamp")
    @JoinColumn(name = "id_champ", insertable = false, updatable = false)
	private Champion champ;
	   
	@Column(name = "descr")
	private String descr;
	
	@Column(name = "power_pick")
	private boolean powerPick;
	
    public ChampRole() {
    	
    }

	public ChampRole(TeamComp comp, Champion champ, String role, String descr, boolean powerPick) {
		this.idChampRole = new ChampRoleEmbedded(comp.getIdComp(), champ.getIdChamp(), role);
		this.comp = comp;
		this.champ = champ;
		this.descr = descr;
		this.powerPick = powerPick;
	}

	public ChampRoleEmbedded getIdChampRole() {
		return idChampRole;
	}

	public void setIdChampRole(ChampRoleEmbedded idChampRole) {
		this.idChampRole = idChampRole;
	}

	public TeamComp getComp() {
		return comp;
	}

	public void setComp(TeamComp comp) {
		this.comp = comp;
	}

	public Champion getChamp() {
		return champ;
	}

	public void setChamp(Champion champ) {
		this.champ = champ;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public boolean isPowerPick() {
		return powerPick;
	}

	public void setPowerPick(boolean powerPick) {
		this.powerPick = powerPick;
	}

	@Override
	public int hashCode() {
		return Objects.hash(champ, comp, descr, idChampRole, powerPick);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChampRole other = (ChampRole) obj;
		return Objects.equals(champ, other.champ) && Objects.equals(comp, other.comp)
				&& Objects.equals(descr, other.descr) && Objects.equals(idChampRole, other.idChampRole)
				&& powerPick == other.powerPick;
	}

	
}
