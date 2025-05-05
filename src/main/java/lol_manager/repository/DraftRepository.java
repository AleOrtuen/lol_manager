package lol_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lol_manager.model.Draft;

public interface DraftRepository extends JpaRepository<Draft, Long> {

    @Query("SELECT d FROM Draft d WHERE d.teamBlue.idTeam = :idTeam OR d.teamRed.idTeam = :idTeam")
    public List<Draft> findByIdTeam(@Param("idTeam") Long idTeam);
    
    public List<Draft> findByGameIdGame(Long idGame);
}
