package lol_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import lol_manager.model.Pick;

public interface PickRepository extends JpaRepository<Pick, Long> {

	public List<Pick> findByDraftIdDraft(Long idDraft);
	
	public List<Pick> findBySide(String side);
	
	public List<Pick> findByPickIdChamp(Long idChamp);

	public List<Pick> findByDraftGameIdGame(Long idGame);

	@Query("SELECT p FROM Pick p JOIN p.draft d " +
			"WHERE d.teamBlue.idTeam = :idTeam OR d.teamRed.idTeam = :idTeam")
	List<Pick> findAllPicksByIdTeam(@Param("idTeam") Long idTeam);

	@Query("SELECT p FROM Pick p " +
			"JOIN p.draft d " +
			"WHERE (d.teamBlue.idTeam = :idTeam AND p.side = 'blue') " +
			"   OR (d.teamRed.idTeam = :idTeam AND p.side = 'red')")
	List<Pick> findChosenPicksByIdTeam(@Param("idTeam") Long idTeam);

	
}
