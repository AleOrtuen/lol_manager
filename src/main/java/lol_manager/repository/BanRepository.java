package lol_manager.repository;

import java.util.List;

import lol_manager.model.Pick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import lol_manager.model.Ban;


public interface BanRepository extends JpaRepository<Ban, Long> {

	public List<Ban> findByDraftIdDraft(Long idDraft);
	
	public List<Ban> findBySide(String side);
	
	public List<Ban> findByBanIdChamp(Long idChamp);

	@Query("SELECT b FROM Ban b JOIN b.draft d " +
			"WHERE d.teamBlue.idTeam = :teamId OR d.teamRed.idTeam = :teamId")
	List<Ban> findAllBansByTeamId(@Param("teamId") Long teamId);

}
