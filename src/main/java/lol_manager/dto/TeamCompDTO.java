package lol_manager.dto;

public class TeamCompDTO {

	private Long idComp;
	private TeamDTO team;
	private String name;
	private String descr;
	
	public Long getIdComp() {
		return idComp;
	}
	public void setIdComp(Long idComp) {
		this.idComp = idComp;
	}
	public TeamDTO getTeam() {
		return team;
	}
	public void setTeam(TeamDTO team) {
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
	
	
}
