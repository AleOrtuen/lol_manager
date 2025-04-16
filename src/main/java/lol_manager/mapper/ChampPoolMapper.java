package lol_manager.mapper;

import lol_manager.dto.ChampPoolDTO;
import lol_manager.model.ChampPool;

public class ChampPoolMapper extends BasicMapper<ChampPool, ChampPoolDTO>{


	public ChampPool entityFromDto(ChampPoolDTO dto) throws Exception {
		ChampPool entity = new ChampPool();	
		entity.setUser(MapperManager.USERMAPPER.entityFromDto(dto.getUser())); 
		entity.setChamp(MapperManager.CHAMPMAPPER.entityFromDto(dto.getChampion()));
		return entity;
	}
	
	public ChampPoolDTO dtoFromEntity(ChampPool entity) throws Exception {
		ChampPoolDTO dto = new ChampPoolDTO();
		dto.setIdUser(MapperManager.USERMAPPER.dtoFromEntity(entity.getUser()).getIdUser());
		dto.setIdChamp(MapperManager.CHAMPMAPPER.dtoFromEntity(entity.getChamp()).getIdChamp());
		return dto;		
	}
	
}
