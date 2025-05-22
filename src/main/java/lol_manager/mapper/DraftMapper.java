package lol_manager.mapper;

import lol_manager.dto.DraftDTO;
import lol_manager.model.Draft;

public class DraftMapper extends BasicMapper<Draft, DraftDTO> {

	@Override
	public Draft entityFromDto(DraftDTO dto) throws Exception {
		Draft entity = new Draft();
		entity.setIdDraft(dto.getIdDraft());
		if (dto.getGame() != null) {
			entity.setGame(MapperManager.GAMEMAPPER.entityFromDto(dto.getGame()));
		}
		if (dto.getTeamBlue() != null) {
			entity.setTeamBlue(MapperManager.TEAMMAPPER.entityFromDto(dto.getTeamBlue()));
		}
		if (dto.getTeamRed() != null) {
		entity.setTeamRed(MapperManager.TEAMMAPPER.entityFromDto(dto.getTeamRed()));
		}
		if (dto.getWinner() != null) {
			entity.setWinner(MapperManager.TEAMMAPPER.entityFromDto(dto.getWinner()));
		}
		entity.setClosed(dto.isClosed());
		entity.setReady(dto.isReady());
		return entity;
	}

	@Override
	public DraftDTO dtoFromEntity(Draft entity) throws Exception {
		DraftDTO dto = new DraftDTO();
		dto.setIdDraft(entity.getIdDraft());
		if (entity.getGame() != null) {
			dto.setGame(MapperManager.GAMEMAPPER.dtoFromEntity(entity.getGame()));
		}
		if (entity.getTeamBlue() != null) {
			dto.setTeamBlue(MapperManager.TEAMMAPPER.dtoFromEntity(entity.getTeamBlue()));
		}
		if (entity.getTeamRed() != null) {
			dto.setTeamRed(MapperManager.TEAMMAPPER.dtoFromEntity(entity.getTeamRed()));
		}
		if (entity.getWinner() != null) {
			dto.setWinner(MapperManager.TEAMMAPPER.dtoFromEntity(entity.getWinner()));
		}
		dto.setClosed(entity.isClosed());
		dto.setReady(entity.isReady());
		return dto;
	}



}
