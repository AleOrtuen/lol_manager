package lol_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lol_manager.model.ChampPool;
import lol_manager.model.embedded.ChampPoolEmbedded;

public interface ChampPoolRepository extends JpaRepository<ChampPool, ChampPoolEmbedded> {
	
	public List<ChampPool> findByIdChampPool_IdUser(Long idUser);
	
	public List<ChampPool> findByIdChampPool_IdChamp(Long idChamp);
	
}
