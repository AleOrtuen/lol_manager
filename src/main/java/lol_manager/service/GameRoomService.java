package lol_manager.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lol_manager.dto.GameRoomDTO;
import lol_manager.mapper.MapperManager;
import lol_manager.model.GameRoom;
import lol_manager.repository.GameRoomRepository;

@Service
public class GameRoomService {

	@Autowired
	private GameRoomRepository gameRoomRepository;
	
	@Autowired
	private GameService gameService;
	
	public GameRoomDTO save(GameRoomDTO gameRoomDto) throws Exception {
		String idRoom = UUID.randomUUID().toString();
		GameRoom room = new GameRoom(idRoom);
		if (gameRoomDto.getGame().getIdGame() != null) {
			gameService.findById(gameRoomDto.getGame().getIdGame());
			room.setGame(MapperManager.GAMEMAPPER.entityFromDto(gameRoomDto.getGame()));
		}
		return MapperManager.GAMEROOMMAPPER.dtoFromEntity(gameRoomRepository.save(room));
	}
	
	public GameRoomDTO update(GameRoomDTO gameRoomDto) throws Exception {
		findById(gameRoomDto.getIdRoom());
		if (gameRoomDto.getGame().getIdGame() != null) {
			gameService.findById(gameRoomDto.getGame().getIdGame());
		}
		GameRoom room = MapperManager.GAMEROOMMAPPER.entityFromDto(gameRoomDto);
		return MapperManager.GAMEROOMMAPPER.dtoFromEntity(gameRoomRepository.save(room));
	}
	
	public void delete(String idRoom) throws Exception {
		findById(idRoom);
		gameRoomRepository.deleteById(idRoom);
	}
	
	public List<GameRoomDTO> findAll() throws Exception {
		List<GameRoom> rooms = gameRoomRepository.findAll();
		Assert.isTrue(rooms.size() != 0, "Rooms not found");
		return MapperManager.GAMEROOMMAPPER.dtoFromEntity(rooms);
	}
	
	public GameRoomDTO findById(String idRoom) throws Exception {
		Optional<GameRoom> room = gameRoomRepository.findById(idRoom);
		Assert.isTrue(room.isPresent(), "Room not found");
		return MapperManager.GAMEROOMMAPPER.dtoFromEntity(gameRoomRepository.save(room.get()));
	}
	
	public GameRoomDTO findByIdGame(Long idGame) throws Exception {
		gameService.findById(idGame);
		return MapperManager.GAMEROOMMAPPER.dtoFromEntity(gameRoomRepository.findByGameIdGame(idGame));
	}
}
