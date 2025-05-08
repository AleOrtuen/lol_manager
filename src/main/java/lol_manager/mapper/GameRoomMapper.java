package lol_manager.mapper;

import lol_manager.dto.GameRoomDTO;
import lol_manager.model.GameRoom;

public class GameRoomMapper extends BasicMapper<GameRoom, GameRoomDTO> {

	@Override
	public GameRoom entityFromDto(GameRoomDTO dto) throws Exception {
		GameRoom entity = new GameRoom();
		entity.setIdRoom(dto.getIdRoom());
		entity.setPlayer1Link(dto.getPlayer1Link());
		entity.setPlayer2Link(dto.getPlayer2Link());
		if (dto.getGame() != null) {
			entity.setGame(MapperManager.GAMEMAPPER.entityFromDto(dto.getGame()));
		}
		entity.setDate(dto.getDate());
		return entity;
	}

	@Override
	public GameRoomDTO dtoFromEntity(GameRoom entity) throws Exception {
		GameRoomDTO dto = new GameRoomDTO();
		dto.setIdRoom(entity.getIdRoom());
		dto.setPlayer1Link(entity.getPlayer1Link());
		dto.setPlayer2Link(entity.getPlayer2Link());
		if (entity.getGame() != null) {
			dto.setGame(MapperManager.GAMEMAPPER.dtoFromEntity(entity.getGame()));
		}
		dto.setDate(entity.getDate());
		return dto;
	}

}
