package lol_manager.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_game")
	private Long idGame;
	
    @ManyToOne
    @JoinColumn(name = "id_team1")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "id_team2")
    private Team team2;

    @Column(name = "style")
    private String style;
    
    @Column(name = "fearless")
    private boolean fearless;
    
    @Column(name = "date", insertable = false, updatable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime date;
    
    @ManyToOne
    @JoinColumn(name = "winner")
    private Team winner;
    
    public Game() {
    	
    }
    
    public Game(Team team1, Team team2, String style, boolean fearless) {
    	this.team1 = team1;
    	this.team2 = team2;
    	this.style = style;
    	this.fearless = fearless;
    }

	public Long getIdGame() {
		return idGame;
	}

	public void setIdGame(Long idGame) {
		this.idGame = idGame;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public boolean isFearless() {
		return fearless;
	}

	public void setFearless(boolean fearless) {
		this.fearless = fearless;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Team getWinner() {
		return winner;
	}

	public void setWinner(Team winner) {
		this.winner = winner;
	}
    
    

}
