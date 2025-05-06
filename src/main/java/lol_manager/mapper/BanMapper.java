package lol_manager.mapper;

import lol_manager.dto.BanDTO;
import lol_manager.model.Ban;

public class BanMapper extends BasicMapper<Ban, BanDTO> {

	@Override
	public Ban entityFromDto(BanDTO dto) throws Exception {
		Ban entity = new Ban();
		entity.setIdBan(dto.getIdBan());
		entity.setDraft(MapperManager.DRAFTMAPPER.entityFromDto(dto.getDraft()));
		entity.setSide(dto.getSide());
		entity.setBan(MapperManager.CHAMPMAPPER.entityFromDto(dto.getBan()));
		return entity;
	}

	@Override
	public BanDTO dtoFromEntity(Ban entity) throws Exception {
		BanDTO dto = new BanDTO();
		dto.setIdBan(entity.getIdBan());
		dto.setDraft(MapperManager.DRAFTMAPPER.dtoFromEntity(entity.getDraft()));
		dto.setSide(entity.getSide());
		dto.setBan(MapperManager.CHAMPMAPPER.dtoFromEntity(entity.getBan()));
		return dto;
	}

}
