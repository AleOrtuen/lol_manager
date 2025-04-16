package lol_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lol_manager.model.TeamComp;

public interface TeamCompRepository extends JpaRepository<TeamComp, Long> {

	public List<TeamComp> findByTeam_IdTeam(Long idTeam); 
	
	public List<TeamComp> findByName(String name);
	
}
