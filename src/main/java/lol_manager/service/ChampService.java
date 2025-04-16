package lol_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lol_manager.dto.ChampDTO;
import lol_manager.mapper.MapperManager;
import lol_manager.model.Champion;
import lol_manager.repository.ChampRepository;
import lol_manager.validation.Validations;

@Service
public class ChampService {

	@Autowired
	private ChampRepository champRepository;
	
	public ChampDTO save(ChampDTO c) throws Exception {
		Assert.isTrue(Validations.validChamp(c), "Invalid form");
		Champion champion = MapperManager.CHAMPMAPPER.entityFromDto(c);
		Assert.isTrue(champRepository.findByName(c.getName()) == null, "Existing champ");
		return MapperManager.CHAMPMAPPER.dtoFromEntity(champRepository.save(champion));
	}
	
	public ChampDTO update(ChampDTO c) throws Exception {
		findById(c.getIdChamp());
		Assert.isTrue(Validations.validChamp(c), "Invalid form");
		Champion champion = MapperManager.CHAMPMAPPER.entityFromDto(c);
		return MapperManager.CHAMPMAPPER.dtoFromEntity(champRepository.save(champion));
	}
	
	public void delete(Long idChamp) throws Exception {
		findById(idChamp);
		champRepository.deleteById(idChamp);
	}
	
	public List<ChampDTO> findAll() throws Exception {
		List<Champion> champions = champRepository.findAll();
		Assert.isTrue(champions.size() != 0 , "No champions found");
		return MapperManager.CHAMPMAPPER.dtoFromEntity(champions);
	}
	
	public List<ChampDTO> findByIdComp(Long idComp) throws Exception {
		List<Champion> champions = champRepository.findByIdComp(idComp);
		Assert.isTrue(champions.size() != 0 , "No champions found");
		return MapperManager.CHAMPMAPPER.dtoFromEntity(champions);
	}
	
	public ChampDTO findById(Long idChamp) throws Exception {
		Optional<Champion> champion = champRepository.findById(idChamp);
		Assert.isTrue(champion.isPresent(), "Champion not found");
		return MapperManager.CHAMPMAPPER.dtoFromEntity(champion.get());
	}
	
	public ChampDTO findByName(String name) throws Exception {
		Champion champ = champRepository.findByName(name);
		Assert.isTrue(champ != null, "Champion not found");
		return MapperManager.CHAMPMAPPER.dtoFromEntity(champ);
	}
	

	

}
