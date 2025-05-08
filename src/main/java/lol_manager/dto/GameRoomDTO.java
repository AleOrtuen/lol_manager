package lol_manager.dto;

import java.time.LocalDateTime;

public class GameRoomDTO {

	private String idRoom;
	private String player1Link;
	private String player2Link;
	private GameDTO game;
	private LocalDateTime date;
	
	public String getIdRoom() {
		return idRoom;
	}
	public void setIdRoom(String idRoom) {
		this.idRoom = idRoom;
	}
	public String getPlayer1Link() {
		return player1Link;
	}
	public void setPlayer1Link(String player1Link) {
		this.player1Link = player1Link;
	}
	public String getPlayer2Link() {
		return player2Link;
	}
	public void setPlayer2Link(String player2Link) {
		this.player2Link = player2Link;
	}
	public GameDTO getGame() {
		return game;
	}
	public void setGame(GameDTO game) {
		this.game = game;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
	
}
