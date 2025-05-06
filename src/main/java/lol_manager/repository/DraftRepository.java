package lol_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lol_manager.model.Champion;
import lol_manager.model.Draft;

public interface DraftRepository extends JpaRepository<Draft, Long> {

    @Query("SELECT d FROM Draft d WHERE d.teamBlue.idTeam = :idTeam OR d.teamRed.idTeam = :idTeam")
    public List<Draft> findByIdTeam(@Param("idTeam") Long idTeam);
    
    public List<Draft> findByGameIdGame(Long idGame);
    
    @Query("""
    	    SELECT c FROM Champion c
    	    WHERE c.idChamp NOT IN (
    	        SELECT b.ban.idChamp FROM Ban b
    	        WHERE b.draft.idDraft = :idDraft
    	    )
    	    AND c.idChamp NOT IN (
    	        SELECT p.pick.idChamp FROM Pick p
    	        WHERE p.draft.game.idGame = (
    	            SELECT d.game.idGame FROM Draft d WHERE d.idDraft = :idDraft
    	        )
    	        AND p.draft.game.fearless = true
    	    )
    	""")
    public List<Champion> findAvailableChampionsByDraftId(@Param("idDraft") Long idDraft);

}
