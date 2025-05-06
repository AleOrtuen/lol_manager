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
@Table(name = "picks")
public class Pick {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pick")
	private Long idPick;
	
    @ManyToOne
    @JoinColumn(name = "id_draft")
    private Draft draft;

    @Column(name = "side")
    private String side;

    @ManyToOne
    @JoinColumn(name = "pick")
    private Champion pick;

    public Pick() {
    	
    }
    
	public Pick(Draft draft, String side, Champion pick) {
		super();
		this.draft = draft;
		this.side = side;
		this.pick = pick;
	}

	public Long getIdPick() {
		return idPick;
	}

	public void setIdPick(Long idPick) {
		this.idPick = idPick;
	}

	public Draft getDraft() {
		return draft;
	}

	public void setDraft(Draft draft) {
		this.draft = draft;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public Champion getPick() {
		return pick;
	}

	public void setPick(Champion pick) {
		this.pick = pick;
	}
    
  
}
