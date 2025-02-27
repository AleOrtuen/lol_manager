package lol_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lol_manager.model.Champion;
import lol_manager.repository.ChampRepository;
import lol_manager.validation.Validations;

@Service
public class ChampService {

	@Autowired
	private ChampRepository champRepository;
	
	public Champion save(Champion c) {
		Assert.isTrue(Validations.validChamp(c), "Invalid form");
		Assert.isTrue(champRepository.findByName(c.getName()) == null, "Existing champ");
		return champRepository.save(c);
	}
	
	public Champion update(Champion c) {
		findById(c.getIdChamp());
		Assert.isTrue(Validations.validChamp(c), "Invalid form");
		return champRepository.save(c);
	}
	
	public void delete(Long idChamp) {
		findById(idChamp);
		champRepository.deleteById(idChamp);
	}
	
	public List<Champion> findAll() {
		List<Champion> champions = champRepository.findAll();
		Assert.isTrue(champions.size() != 0 , "No champions found");
		return champions;
	}
	
	public Champion findById(Long idChamp) {
		Optional<Champion> champion = champRepository.findById(idChamp);
		Assert.isTrue(champion.isPresent(), "Champion not found");
		return champion.get();
	}
	
	public Champion findByName(String name) {
		Champion champ = champRepository.findByName(name);
		Assert.isTrue(champ != null, "Champion not found");
		return champ;
	}
	

	

}
