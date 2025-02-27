package lol_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lol_manager.model.ChampPool;
import lol_manager.repository.ChampPoolRepository;

@Service
public class ChampPoolService {

	@Autowired
	private ChampPoolRepository champPoolRepository;
	
	public ChampPool save(ChampPool c) {
		return champPoolRepository.save(c);
	}
	
//	public ChampPool update(ChampPool c) {
//		findById(c.getIdChamp());
//		Assert.isTrue(Validations.validChamp(c), "Invalid form");
//		return champPoolRepository.save(c);
//	}
//	
//	public void delete(Long idChamp) {
//		findById(idChamp);
//		champPoolRepository.deleteById(idChamp);
//	}
}
