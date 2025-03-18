package lol_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lol_manager.model.Champion;
import lol_manager.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

	public Team findByName(String name);
	
	public Team findByTag(String tag);
	
	@Query(value = "SELECT DISTINCT c.* FROM champions c " +
            "JOIN champ_pool cp ON c.id_champ = cp.id_champ " +
            "JOIN users u ON cp.id_user = u.id_user " +
            "JOIN team_members tm ON u.id_user = tm.id_user " +
            "WHERE tm.id_team = :teamId", nativeQuery = true)
	public List<Champion> teamChampions(@Param("teamId") Long teamId);
}
