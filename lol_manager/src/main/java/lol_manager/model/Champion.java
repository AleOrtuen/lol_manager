package lol_manager.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "champions")
public class Champion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_champ")
	private Long idChamp;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "img", nullable = false)
	private String img;

    @OneToMany(mappedBy = "idChampPool.idChamp", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<ChampPool> champPools;
    
	public Champion() {
		
	}
	
	public Champion(String name, String img) {
		this.name = name;
		this.img = img;
	}

	public Long getIdChamp() {
		return idChamp;
	}

	public void setIdChamp(Long idChamp) {
		this.idChamp = idChamp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<ChampPool> getChampPools() {
		return champPools;
	}

	public void setChampPools(List<ChampPool> champPools) {
		this.champPools = champPools;
	}
	
	
}
