package lol_manager.dto;

public class ChampRoleDTO {

	private ChampDTO champion;
	private TeamCompDTO comp;
	private String role;
	private String descr;
	private boolean powerPick;
	
	public ChampDTO getChampion() {
		return champion;
	}
	public void setChampion(ChampDTO champion) {
		this.champion = champion;
	}
	public TeamCompDTO getComp() {
		return comp;
	}
	public void setComp(TeamCompDTO comp) {
		this.comp = comp;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	
	
}
