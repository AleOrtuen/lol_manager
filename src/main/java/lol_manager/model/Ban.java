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
@Table(name = "bans")
public class Ban {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ban")
	private Long idBan;
	
    @ManyToOne
    @JoinColumn(name = "id_draft")
    private Draft draft;

    @Column(name = "side")
    private String side;

    @ManyToOne
    @JoinColumn(name = "ban")
    private Champion ban;

    public Ban() {
    	
    }
    
	public Ban(Draft draft, String side, Champion ban) {
		this.draft = draft;
		this.side = side;
		this.ban = ban;
	}

	public Long getIdBan() {
		return idBan;
	}

	public void setIdBan(Long idBan) {
		this.idBan = idBan;
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

	public Champion getBan() {
		return ban;
	}

	public void setBan(Champion ban) {
		this.ban = ban;
	}
    
    
}
