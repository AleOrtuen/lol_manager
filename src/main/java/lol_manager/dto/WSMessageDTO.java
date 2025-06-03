package lol_manager.dto;

import lol_manager.model.Champion;

public class WSMessageDTO {

	private String idRoom;
	private String side;
	private ChampDTO champion;
	private String type;
	private String sender;
	private Long startTime;
	private GameDTO game;
	private DraftDTO draft;
	private DraftEventsDTO events;

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
	public ChampDTO getChampion() {
		return champion;
	}
	public void setChampion(ChampDTO champion) {
		this.champion = champion;
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
	public GameDTO getGame() {
		return game;
	}
	public void setGame(GameDTO game) {
		this.game = game;
	}
	public DraftDTO getDraft() {
		return draft;
	}
	public void setDraft(DraftDTO draft) {
		this.draft = draft;
	}
	public DraftEventsDTO getEvents() {
		return events;
	}
	public void setEvents(DraftEventsDTO events) {
		this.events = events;
	}
}
