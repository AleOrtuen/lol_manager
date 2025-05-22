package lol_manager.dto;

public class DraftDTO {

	private Long idDraft;
	private GameDTO game;
	private TeamDTO teamBlue;
	private TeamDTO teamRed;
	private TeamDTO winner;
	private boolean closed;
	private boolean ready;
	
	public Long getIdDraft() {
		return idDraft;
	}
	public void setIdDraft(Long idDraft) {
		this.idDraft = idDraft;
	}
	public GameDTO getGame() {
		return game;
	}
	public void setGame(GameDTO game) {
		this.game = game;
	}
	public TeamDTO getTeamBlue() {
		return teamBlue;
	}
	public void setTeamBlue(TeamDTO teamBlue) {
		this.teamBlue = teamBlue;
	}
	public TeamDTO getTeamRed() {
		return teamRed;
	}
	public void setTeamRed(TeamDTO teamRed) {
		this.teamRed = teamRed;
	}
	public TeamDTO getWinner() {
		return winner;
	}
	public void setWinner(TeamDTO winner) {
		this.winner = winner;
	}
	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	public boolean isReady() {
		return ready;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
	}
}
