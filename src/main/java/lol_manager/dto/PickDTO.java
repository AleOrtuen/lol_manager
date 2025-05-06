package lol_manager.dto;

public class PickDTO {

	private Long idPick;
	private DraftDTO draft;
	private String side;
	private ChampDTO pick;
	
	public Long getIdPick() {
		return idPick;
	}
	public void setIdPick(Long idPick) {
		this.idPick = idPick;
	}
	public DraftDTO getDraft() {
		return draft;
	}
	public void setDraft(DraftDTO draft) {
		this.draft = draft;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public ChampDTO getPick() {
		return pick;
	}
	public void setPick(ChampDTO pick) {
		this.pick = pick;
	}
	
	
}
