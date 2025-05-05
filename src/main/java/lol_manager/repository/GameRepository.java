package lol_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lol_manager.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game g WHERE g.team1.idTeam = :idTeam OR g.team2.idTeam = :idTeam")
    public List<Game> findByIdTeam(@Param("idTeam") Long idTeam);
	
}
