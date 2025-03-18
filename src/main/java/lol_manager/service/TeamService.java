package lol_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lol_manager.dto.ChampDTO;
import lol_manager.dto.ChampRoleDTO;
import lol_manager.dto.TeamDTO;
import lol_manager.mapper.MapperManager;
import lol_manager.model.ChampRole;
import lol_manager.model.Champion;
import lol_manager.model.Team;
import lol_manager.repository.TeamRepository;
import lol_manager.utility.ChampRoleUtility;
import lol_manager.validation.Validations;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
//	@Autowired
//	private ChampService champService;
	
	public TeamDTO save(TeamDTO t) throws Exception {
		Assert.isTrue(Validations.validTeam(t), "Invalid form");
		Team team = MapperManager.TEAMMAPPER.entityFromDto(t);
		Assert.isTrue(teamRepository.findByName(t.getName()) == null, "Existing name");
		Assert.isTrue(teamRepository.findByTag(t.getTag()) == null, "Existing tag");
		return MapperManager.TEAMMAPPER.dtoFromEntity(teamRepository.save(team));
	}

	public TeamDTO update(TeamDTO t) throws Exception {
		findById(t.getIdTeam());
		Assert.isTrue(Validations.validTeam(t), "Invalid form");
		Team team = MapperManager.TEAMMAPPER.entityFromDto(t);
		return MapperManager.TEAMMAPPER.dtoFromEntity(teamRepository.save(team));
	}
	
	public void delete(Long idTeam) throws Exception {
		findById(idTeam);
		teamRepository.deleteById(idTeam);
	}
	
	public List<TeamDTO> findAll() throws Exception {
		List<Team> teams = teamRepository.findAll();
		Assert.isTrue(teams.size() != 0 , "Teams not found");
		return MapperManager.TEAMMAPPER.dtoFromEntity(teams);
	}
	
	public TeamDTO findById(Long idTeam) throws Exception {
		Optional<Team> team = teamRepository.findById(idTeam);
		Assert.isTrue(team.isPresent(), "Team not found");
		return MapperManager.TEAMMAPPER.dtoFromEntity(team.get());
	}
	
	public TeamDTO findByName(String name) throws Exception {
		Team team = teamRepository.findByName(name);
		Assert.isTrue(team != null, "Team not found");
		return MapperManager.TEAMMAPPER.dtoFromEntity(team);
	}
	
	public TeamDTO findByTag(String tag) throws Exception {
		Team team = teamRepository.findByTag(tag);
		Assert.isTrue(team !=null, "Team not found");
		return MapperManager.TEAMMAPPER.dtoFromEntity(team);
	}
	
	public List<ChampDTO> findChampions(Long idTeam) throws Exception {
		List<Champion> champs = teamRepository.teamChampions(idTeam);
		Assert.isTrue(champs.size() != 0, "Champs not found");
		return MapperManager.CHAMPMAPPER.dtoFromEntity(champs);
	}
	
	public List<ChampRoleDTO> compCombinator(List<ChampRoleDTO> oldList, List<ChampRoleDTO> champRoles) throws Exception {
		List<ChampRole> combinations = ChampRoleUtility.validCombinations(
				MapperManager.CHAMPROLEMAPPER.entityFromDto(oldList), 
				MapperManager.CHAMPROLEMAPPER.entityFromDto(champRoles));
		return MapperManager.CHAMPROLEMAPPER.dtoFromEntity(combinations);
	}
}	
