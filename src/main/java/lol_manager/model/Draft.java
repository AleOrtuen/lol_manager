package lol_manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "drafts")
public class Draft {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_draft")
	private Long idDraft;
	
    @ManyToOne
    @JoinColumn(name = "id_game")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "blue_side")
    private Team teamBlue;

    @ManyToOne
    @JoinColumn(name = "red_side")
    private Team teamRed;
    
    @ManyToOne
    @JoinColumn(name = "winner")
    private Team winner;
    
    @Column(name = "closed")
	private boolean closed;

	@Column(name ="ready")
	private boolean ready;
    
    public Draft() {
    	
    }
    
    public Draft(Game game, Team teamBlue, Team teamRed) {
    	this.game = game;
    	this.teamBlue = teamBlue;
    	this.teamRed = teamRed;
    }

	public Long getIdDraft() {
		return idDraft;
	}

	public void setIdDraft(Long idDraft) {
		this.idDraft = idDraft;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Team getTeamBlue() {
		return teamBlue;
	}

	public void setTeamBlue(Team teamBlue) {
		this.teamBlue = teamBlue;
	}

	public Team getTeamRed() {
		return teamRed;
	}

	public void setTeamRed(Team teamRed) {
		this.teamRed = teamRed;
	}

	public Team getWinner() {
		return winner;
	}

	public void setWinner(Team winner) {
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
