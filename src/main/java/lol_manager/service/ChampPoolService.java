package lol_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lol_manager.dto.ChampPoolDTO;
import lol_manager.mapper.MapperManager;
import lol_manager.model.ChampPool;
import lol_manager.model.embedded.ChampPoolEmbedded;
import lol_manager.repository.ChampPoolRepository;
import lol_manager.validation.Validations;

@Service
public class ChampPoolService {

	@Autowired
	private ChampPoolRepository champPoolRepository;
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private ChampService champService;
	
	
	public ChampPoolDTO save(ChampPoolDTO c) throws Exception {
		Assert.isTrue(Validations.isValidChampPool(c), "Invalid form");
		userService.findById(c.getUser().getIdUser());
		champService.findById(c.getChampion().getIdChamp());
		ChampPool champPool = MapperManager.CHAMPPOOLMAPPER.entityFromDto(c);
		return MapperManager.CHAMPPOOLMAPPER.dtoFromEntity(champPoolRepository.save(champPool));
	}
	
	public void delete(Long idUser, Long idChamp) throws Exception {
		findById(idUser, idChamp);
		champPoolRepository.deleteById(new ChampPoolEmbedded(idUser, idChamp));
	}
	
	public ChampPoolDTO findById(Long idUser, Long idChamp) throws Exception {
		Optional<ChampPool> pool = champPoolRepository.findById(new ChampPoolEmbedded(idUser, idChamp));
		Assert.isTrue(pool.isPresent(), "Champ pool not found");
		return MapperManager.CHAMPPOOLMAPPER.dtoFromEntity(pool.get());
	}
	
	public List<ChampPoolDTO> findByIdUser(Long idUser) throws Exception {
		List<ChampPool> pool = champPoolRepository.findByIdChampPool_IdUser(idUser);
		Assert.isTrue(pool.size() != 0, "Champ pool not found");
		return MapperManager.CHAMPPOOLMAPPER.dtoFromEntity(pool);
	}
	
	public List<ChampPoolDTO> findByIdChamp(Long idChamp) throws Exception {
		List<ChampPool> pool = champPoolRepository.findByIdChampPool_IdChamp(idChamp);
		Assert.isTrue(pool.size() != 0, "Champ pool not found");
		return MapperManager.CHAMPPOOLMAPPER.dtoFromEntity(pool);
	}
	
	public List<ChampPoolDTO> findAll() throws Exception {
		List<ChampPool> champPools = champPoolRepository.findAll();
		Assert.isTrue(champPools.size() != 0 , "No champ pools found");
		return MapperManager.CHAMPPOOLMAPPER.dtoFromEntity(champPools);
	}
}
