package lol_manager.mapper;

import lol_manager.dto.ChampDTO;
import lol_manager.model.Champion;

public class ChampMapper extends BasicMapper<Champion, ChampDTO>{

	@Override
	public Champion entityFromDto(ChampDTO dto) throws Exception {
		Champion entity = new Champion();
		entity.setIdChamp(dto.getIdChamp());
		entity.setName(dto.getName());
		entity.setImg(dto.getImg());
		return entity;
	}
	
	@Override
	public ChampDTO dtoFromEntity(Champion entity) throws Exception {
		ChampDTO dto = new ChampDTO();
		dto.setIdChamp(entity.getIdChamp());
		dto.setName(entity.getName());
		dto.setImg(entity.getImg());
		return dto;
	}

}
