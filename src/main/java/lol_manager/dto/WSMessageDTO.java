package lol_manager.dto;

public class WSMessageDTO {

	private String idRoom;
	private String side;
	private Long idChamp;
	
	public String getIdRoom() {
		return idRoom;
	}
	public void setIdRoom(String idRoom) {
		this.idRoom = idRoom;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public Long getIdChamp() {
		return idChamp;
	}
	public void setIdChamp(Long idChamp) {
		this.idChamp = idChamp;
	}
	
	
}
