package lol_manager.dto;

import java.util.Objects;

public class ChampDTO {

	private Long idChamp;
	private String name;
	private String img;
	
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChampDTO champDTO = (ChampDTO) o;
		return Objects.equals(idChamp, champDTO.idChamp) && Objects.equals(name, champDTO.name) && Objects.equals(img, champDTO.img);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idChamp, name, img);
	}
}
