package lol_manager.mapper;

import lol_manager.dto.ChampRoleDTO;
import lol_manager.model.ChampRole;

public class ChampRoleMapper extends BasicMapper<ChampRole, ChampRoleDTO> {

	@Override
	public ChampRole entityFromDto(ChampRoleDTO dto) throws Exception {
		ChampRole entity = new ChampRole();
		entity.setChamp(MapperManager.CHAMPMAPPER.entityFromDto(dto.getChampion()));
		entity.setComp(MapperManager.TEAMCOMPMAPPER.entityFromDto(dto.getComp()));
	    entity.getIdChampRole().setIdChamp(dto.getChampion().getIdChamp());
	    entity.getIdChampRole().setIdComp(dto.getComp().getIdComp());
	    entity.getIdChampRole().setRole(dto.getRole());
		entity.setDescr(dto.getDescr());
		entity.setPowerPick(dto.isPowerPick());
		return entity;
	}

	@Override
	public ChampRoleDTO dtoFromEntity(ChampRole entity) throws Exception {
		ChampRoleDTO dto = new ChampRoleDTO();
		dto.setChampion(MapperManager.CHAMPMAPPER.dtoFromEntity(entity.getChamp()));
		dto.setComp(MapperManager.TEAMCOMPMAPPER.dtoFromEntity(entity.getComp()));
		dto.setRole(entity.getIdChampRole().getRole());
		dto.setDescr(entity.getDescr());
		dto.setPowerPick(entity.isPowerPick());
		return dto;
	}

}
