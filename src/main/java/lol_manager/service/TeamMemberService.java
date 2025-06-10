package lol_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lol_manager.dto.TeamMemberDTO;
import lol_manager.mapper.MapperManager;
import lol_manager.model.TeamMember;
import lol_manager.model.embedded.TeamMemberEmbedded;
import lol_manager.repository.TeamMemberRepository;
import lol_manager.validation.Validations;

@Service
public class TeamMemberService {

	@Autowired
	private TeamMemberRepository teamMemberRepository;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private UserService userService;
	
	
	public TeamMemberDTO save(TeamMemberDTO t) throws Exception {
		Assert.isTrue(Validations.isValidTeamMember(t), "Invalid form");
		teamService.findById(t.getTeam().getIdTeam());
		userService.findById(t.getUser().getIdUser());
		TeamMember teamMember = MapperManager.TEAMMEMBERMAPPER.entityFromDto(t);
		return MapperManager.TEAMMEMBERMAPPER.dtoFromEntity(teamMemberRepository.save(teamMember));
	}
	
	public void delete(Long idUser, Long idTeam) throws Exception {
		findById(idUser, idTeam);
		teamMemberRepository.deleteById(new TeamMemberEmbedded(idUser, idTeam));
	}
	
	public TeamMemberDTO findById(Long idUser, Long idTeam) throws Exception {
		Optional<TeamMember> member = teamMemberRepository.findById(new TeamMemberEmbedded(idUser, idTeam));
		Assert.isTrue(member.isPresent(), "Team member not found");
		return MapperManager.TEAMMEMBERMAPPER.dtoFromEntity(member.get());
	}
	
	public List<TeamMemberDTO> findByIdUser(Long idUser) throws Exception {
		List<TeamMember> member = teamMemberRepository.findByIdTeamMember_idUser(idUser);
		Assert.isTrue(member.size() != 0, "Team members not found");
		return MapperManager.TEAMMEMBERMAPPER.dtoFromEntity(member);
	}
	
	public List<TeamMemberDTO> findByIdTeam(Long idTeam) throws Exception {
		List<TeamMember> member = teamMemberRepository.findByIdTeamMember_idTeam(idTeam);
		Assert.isTrue(member.size() != 0, "Team members not found");
		return MapperManager.TEAMMEMBERMAPPER.dtoFromEntity(member);
	}
	
	public List<TeamMemberDTO> findAll() throws Exception {
		List<TeamMember> teamMembers = teamMemberRepository.findAll();
		Assert.isTrue(teamMembers.size() != 0 , "No team members found");
		return MapperManager.TEAMMEMBERMAPPER.dtoFromEntity(teamMembers);
	}
}
