package lol_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lol_manager.dto.GameDTO;
import lol_manager.mapper.MapperManager;
import lol_manager.model.Game;
import lol_manager.repository.GameRepository;
import lol_manager.validation.Validations;

@Service
public class GameService {

	@Autowired 
	private GameRepository gameRepository;
	
	@Autowired
	private TeamService teamService;
	
	public GameDTO save(GameDTO gameDto) throws Exception {
		Assert.isTrue(Validations.validGame(gameDto), "Invalid game form");
		Game game = MapperManager.GAMEMAPPER.entityFromDto(gameDto);
		teamService.findById(game.getTeam1().getIdTeam());
		teamService.findById(game.getTeam2().getIdTeam());		
		return MapperManager.GAMEMAPPER.dtoFromEntity(gameRepository.save(game));
	}
	
	public GameDTO update(GameDTO gameDto) throws Exception {
		findById(gameDto.getIdGame());
		Assert.isTrue(Validations.validGame(gameDto), "Invalid game form");
		Game game = MapperManager.GAMEMAPPER.entityFromDto(gameDto);
		teamService.findById(game.getTeam1().getIdTeam());
		teamService.findById(game.getTeam2().getIdTeam());
		return MapperManager.GAMEMAPPER.dtoFromEntity(gameRepository.save(game));
	}
	
	public void delete(Long idGame) throws Exception {
		findById(idGame);
		gameRepository.deleteById(idGame);
	}
	
	public List<GameDTO> findAll() throws Exception {
		List<Game> games = gameRepository.findAll();
		Assert.isTrue(games.size() != 0, "Games not found");
		return MapperManager.GAMEMAPPER.dtoFromEntity(games);
	}
	
	public GameDTO findById(Long idGame) throws Exception {
		Optional<Game> game = gameRepository.findById(idGame);
		Assert.isTrue(game.isPresent(), "Game not found");
		return MapperManager.GAMEMAPPER.dtoFromEntity(game.get());
	}
	
	public List<GameDTO> findByIdTeam(Long idTeam) throws Exception {
		teamService.findById(idTeam);
		return MapperManager.GAMEMAPPER.dtoFromEntity(gameRepository.findByIdTeam(idTeam));
	}
	
	public GameDTO setWinner(GameDTO gameDto) throws Exception {
		Assert.isTrue(gameDto.getWinner() != null, "No winner idTeam");
		teamService.findById(gameDto.getWinner().getIdTeam());
		return update(gameDto);
	}
}
