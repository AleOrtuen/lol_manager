package lol_manager.dto;

public class BanDTO {

	private Long idBan;
	private DraftDTO draft;
	private String side;
	private ChampDTO ban;
	
	public Long getIdBan() {
		return idBan;
	}
	public void setIdBan(Long idBan) {
		this.idBan = idBan;
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
	public ChampDTO getBan() {
		return ban;
	}
	public void setBan(ChampDTO ban) {
		this.ban = ban;
	}
	
}
