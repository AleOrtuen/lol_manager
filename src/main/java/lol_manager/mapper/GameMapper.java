package lol_manager.mapper;

import lol_manager.dto.GameDTO;
import lol_manager.model.Game;

public class GameMapper extends BasicMapper<Game, GameDTO> {

	@Override
	public Game entityFromDto(GameDTO dto) throws Exception {
		Game entity = new Game();
		entity.setIdGame(dto.getIdGame());
		entity.setTeam1(MapperManager.TEAMMAPPER.entityFromDto(dto.getTeam1()));
		entity.setTeam2(MapperManager.TEAMMAPPER.entityFromDto(dto.getTeam2()));
		entity.setStyle(dto.getStyle());
		entity.setFearless(dto.isFearless());
		entity.setDate(dto.getDate());
		entity.setWinner(MapperManager.TEAMMAPPER.entityFromDto(dto.getWinner()));
		return entity;
	}

	@Override
	public GameDTO dtoFromEntity(Game entity) throws Exception {
		GameDTO dto = new GameDTO();
		dto.setIdGame(entity.getIdGame());
		dto.setTeam1(MapperManager.TEAMMAPPER.dtoFromEntity(entity.getTeam1()));
		dto.setTeam2(MapperManager.TEAMMAPPER.dtoFromEntity(entity.getTeam2()));
		dto.setStyle(entity.getStyle());
		dto.setFearless(entity.isFearless());
		dto.setDate(entity.getDate());
		dto.setWinner(MapperManager.TEAMMAPPER.dtoFromEntity(entity.getWinner()));
		return dto;
	}

}
