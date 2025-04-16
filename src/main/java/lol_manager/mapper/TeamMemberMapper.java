package lol_manager.mapper;

import lol_manager.dto.TeamMemberDTO;
import lol_manager.model.TeamMember;

public class TeamMemberMapper extends BasicMapper<TeamMember, TeamMemberDTO> {

	@Override
	public TeamMember entityFromDto(TeamMemberDTO dto) throws Exception {
		TeamMember entity = new TeamMember();
		entity.setUser(MapperManager.USERMAPPER.entityFromDto(dto.getUser()));
		entity.setTeam(MapperManager.TEAMMAPPER.entityFromDto(dto.getTeam()));
		return entity;
	}

	@Override
	public TeamMemberDTO dtoFromEntity(TeamMember entity) throws Exception {
		TeamMemberDTO dto = new TeamMemberDTO();
		dto.setIdUser(MapperManager.USERMAPPER.dtoFromEntity(entity.getUser()).getIdUser());
		dto.setIdTeam(MapperManager.TEAMMAPPER.dtoFromEntity(entity.getTeam()).getIdTeam());
//		dto.setUser(MapperManager.USERMAPPER.dtoFromEntity(entity.getUser()));
//		dto.setTeam(MapperManager.TEAMMAPPER.dtoFromEntity(entity.getTeam()));
		return dto;
	}

//	@Override
//	public List<TeamMember> entityFromDto(List<TeamMemberDTO> dtoList) throws Exception {
//		List<TeamMember> entityList = new ArrayList<>();
//		for (TeamMemberDTO dto : dtoList) {
//			entityList.add(entityFromDto(dto));
//		}
//		return entityList;
//	}
//
//	@Override
//	public List<TeamMemberDTO> dtoFromEntity(List<TeamMember> entityList) throws Exception {
//		List<TeamMemberDTO> dtoList = new ArrayList<>();
//		for (TeamMember entity : entityList) {
//			dtoList.add(dtoFromEntity(entity));
//		}
//		return dtoList;
//	}
}
