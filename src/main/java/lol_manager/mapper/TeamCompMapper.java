package lol_manager.mapper;

import lol_manager.dto.TeamCompDTO;
import lol_manager.model.TeamComp;

public class TeamCompMapper extends BasicMapper<TeamComp, TeamCompDTO> {

	@Override
	public TeamComp entityFromDto(TeamCompDTO dto) throws Exception {
		TeamComp entity = new TeamComp();
		entity.setIdComp(dto.getIdComp());
		entity.setTeam(MapperManager.TEAMMAPPER.entityFromDto(dto.getTeam()));
		entity.setName(dto.getName());
		entity.setDescr(dto.getDescr());
		return entity;
	}

	@Override
	public TeamCompDTO dtoFromEntity(TeamComp entity) throws Exception {
		TeamCompDTO dto = new TeamCompDTO();
		dto.setIdComp(entity.getIdComp());
		dto.setTeam(MapperManager.TEAMMAPPER.dtoFromEntity(entity.getTeam()));
		dto.setName(entity.getName());
		dto.setDescr(entity.getDescr());
		return dto;
	}

}
