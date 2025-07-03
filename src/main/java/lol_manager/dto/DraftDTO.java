package lol_manager.dto;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DraftDTO draftDTO = (DraftDTO) o;
		return closed == draftDTO.closed && ready == draftDTO.ready && Objects.equals(idDraft, draftDTO.idDraft) && Objects.equals(game, draftDTO.game) && Objects.equals(teamBlue, draftDTO.teamBlue) && Objects.equals(teamRed, draftDTO.teamRed) && Objects.equals(winner, draftDTO.winner);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDraft, game, teamBlue, teamRed, winner, closed, ready);
	}
}
