package lol_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lol_manager.model.TeamMember;
import lol_manager.model.embedded.TeamMemberEmbedded;

public interface TeamMemberRepository extends JpaRepository<TeamMember, TeamMemberEmbedded> {

	public List<TeamMember> findByIdTeamMember_idUser(Long idUser);
	
	public List<TeamMember> findByIdTeamMember_idTeam(Long idTeam);
	
}
