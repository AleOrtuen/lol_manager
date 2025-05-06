package lol_manager.mapper;

import lol_manager.dto.PickDTO;
import lol_manager.model.Pick;

public class PickMapper extends BasicMapper<Pick, PickDTO> {

	@Override
	public Pick entityFromDto(PickDTO dto) throws Exception {
		Pick entity = new Pick();
		entity.setIdPick(dto.getIdPick());
		entity.setDraft(MapperManager.DRAFTMAPPER.entityFromDto(dto.getDraft()));
		entity.setSide(dto.getSide());
		entity.setPick(MapperManager.CHAMPMAPPER.entityFromDto(dto.getPick()));
		return entity;
	}

	@Override
	public PickDTO dtoFromEntity(Pick entity) throws Exception {
		PickDTO dto = new PickDTO();
		dto.setIdPick(entity.getIdPick());
		dto.setDraft(MapperManager.DRAFTMAPPER.dtoFromEntity(entity.getDraft()));
		dto.setSide(entity.getSide());
		dto.setPick(MapperManager.CHAMPMAPPER.dtoFromEntity(entity.getPick()));
		return dto;
	}

}
