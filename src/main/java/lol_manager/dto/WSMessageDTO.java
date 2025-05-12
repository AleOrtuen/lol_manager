package lol_manager.dto;

public class WSMessageDTO {

	private String idRoom;
	private String side;
	private Long idChamp;
	private String type;
	private String sender;
	private Long startTime;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	
	
}
