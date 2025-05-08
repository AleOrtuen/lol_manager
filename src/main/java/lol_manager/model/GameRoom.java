package lol_manager.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "game_rooms")
public class GameRoom {

	@Id
	@Column(name = "id_room")
	private String idRoom;
	
	@Column(name = "player1_link")
	private String player1Link;
	
	@Column(name = "player2_link")
	private String player2Link;
	
	@ManyToOne
	@JoinColumn(name = "game")
	private Game game;
	
    @Column(name = "date", insertable = false, updatable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime date;
    
    public GameRoom() {
    	
    }
	
	public GameRoom(String idRoom) {
		this.idRoom = idRoom;
		this.player1Link = "/game/" + idRoom + "/player1";
		this.player2Link = "/game/" + idRoom + "/player2";
		
	}

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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
	
}
