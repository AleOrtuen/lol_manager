package lol_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lol_manager.model.Champion;

public interface ChampRepository extends JpaRepository<Champion, Long> {

	public Champion findByName(String name);
	
	@Query("SELECT DISTINCT c FROM Champion c " +
                  "JOIN ChampRole cr ON cr.champ.idChamp = c.idChamp " +
                  "WHERE cr.comp.idComp = :idComp")
	public List<Champion> findByIdComp(@Param("idComp") Long idComp);
}
