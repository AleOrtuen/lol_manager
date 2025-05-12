package lol_manager.mapper;

import lol_manager.dto.TeamDTO;
import lol_manager.model.Team;

public class TeamMapper extends BasicMapper<Team, TeamDTO> {

	@Override
	public Team entityFromDto(TeamDTO dto) throws Exception {
		Team entity = new Team();
		entity.setIdTeam(dto.getIdTeam());
		entity.setName(dto.getName());
		entity.setTag(dto.getTag());
		entity.setImg(dto.getImg());
		entity.setGuest(dto.isGuest());
		return entity;
	}

	@Override
	public TeamDTO dtoFromEntity(Team entity) throws Exception {
		TeamDTO dto = new TeamDTO();
		dto.setIdTeam(entity.getIdTeam());
		dto.setName(entity.getName());
		dto.setTag(entity.getTag());
		dto.setImg(entity.getImg());
		dto.setGuest(entity.isGuest());
		return dto;
	}

}
