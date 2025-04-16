package lol_manager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lol_manager.model.embedded.ChampPoolEmbedded;

@Entity
@Table(name = "champ_pool")
public class ChampPool {

	@EmbeddedId
	private ChampPoolEmbedded idChampPool = new ChampPoolEmbedded();
	
    @ManyToOne
    @JsonBackReference(value = "user-champpool-reference")
    @MapsId("idUser")
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
	private User user;
	
    @ManyToOne
    @JsonBackReference(value = "champpool-champ-reference")
    @MapsId("idChamp")
    @JoinColumn(name = "id_champ", insertable = false, updatable = false)
	private Champion champ;
	
    public ChampPool() {
    	
    }

	public ChampPool(User user, Champion champion) {
        this.idChampPool = new ChampPoolEmbedded(user.getIdUser(), champion.getIdChamp());
        this.user = user;
        this.champ = champion;
	}

	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Champion getChamp() {
		return champ;
	}

	public void setChamp(Champion champ) {
		this.champ = champ;
	}

	public ChampPoolEmbedded getIdChampPool() {
		return idChampPool;
	}

	public void setIdChampPool(ChampPoolEmbedded idChampPool) {
		this.idChampPool = idChampPool;
	}


    
    
}
