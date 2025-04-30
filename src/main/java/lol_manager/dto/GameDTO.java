package lol_manager.dto;

import java.time.LocalDateTime;

public class GameDTO {

	private Long idGame;
	private TeamDTO team1;
	private TeamDTO team2;
	private String style;
	private boolean fearless;
	private LocalDateTime date;
	private TeamDTO winner;
	
	public Long getIdGame() {
		return idGame;
	}
	public void setIdGame(Long idGame) {
		this.idGame = idGame;
	}
	public TeamDTO getTeam1() {
		return team1;
	}
	public void setTeam1(TeamDTO team1) {
		this.team1 = team1;
	}
	public TeamDTO getTeam2() {
		return team2;
	}
	public void setTeam2(TeamDTO team2) {
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
	public TeamDTO getWinner() {
		return winner;
	}
	public void setWinner(TeamDTO winner) {
		this.winner = winner;
	}
	

}
