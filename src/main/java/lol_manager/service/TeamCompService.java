package lol_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lol_manager.dto.TeamCompDTO;
import lol_manager.mapper.MapperManager;
import lol_manager.model.TeamComp;
import lol_manager.repository.TeamCompRepository;
import lol_manager.validation.Validations;

@Service
public class TeamCompService {

	@Autowired
	private TeamCompRepository teamCompRepository;
	
	@Autowired
	private TeamService teamService;

	public TeamCompDTO save(TeamCompDTO t) throws Exception {
		Assert.isTrue(Validations.isValidTeamComp(t), "Invalid form");
		teamService.findById(t.getTeam().getIdTeam());
		TeamComp teamComp = MapperManager.TEAMCOMPMAPPER.entityFromDto(t);
		return MapperManager.TEAMCOMPMAPPER.dtoFromEntity(teamCompRepository.save(teamComp));
	}
	
	public TeamCompDTO update(TeamCompDTO t) throws Exception {
		findById(t.getIdComp());
		Assert.isTrue(Validations.isValidTeamComp(t), "Invalid form");
		teamService.findById(t.getTeam().getIdTeam());
		TeamComp comp = MapperManager.TEAMCOMPMAPPER.entityFromDto(t);
		return MapperManager.TEAMCOMPMAPPER.dtoFromEntity(teamCompRepository.save(comp));
	}
	
	public void delete(Long idComp) throws Exception {
		findById(idComp);
		teamCompRepository.deleteById(idComp);
	}
	
	public TeamCompDTO findById(Long idComp) throws Exception {
		Optional<TeamComp> comp = teamCompRepository.findById(idComp);
		Assert.isTrue(comp.isPresent(), "Team comp not found");
		return MapperManager.TEAMCOMPMAPPER.dtoFromEntity(comp.get());
	}
	
	public List<TeamCompDTO> findByTeam(Long idTeam) throws Exception {
		List<TeamComp> comps = teamCompRepository.findByTeam_IdTeam(idTeam);
		Assert.isTrue(comps.size() != 0, "Team comps not found");
		return MapperManager.TEAMCOMPMAPPER.dtoFromEntity(comps);
	}
	
	public List<TeamCompDTO> findByName(String name) throws Exception {
		List<TeamComp> comps = teamCompRepository.findByName(name);
		Assert.isTrue(comps.size() != 0, "Team comps not found");
		return MapperManager.TEAMCOMPMAPPER.dtoFromEntity(comps);
	}
	
	public List<TeamCompDTO> findAll() throws Exception {
		List<TeamComp> comps = teamCompRepository.findAll();
		Assert.isTrue(comps.size() != 0 , "No team comps found");
		return MapperManager.TEAMCOMPMAPPER.dtoFromEntity(comps);
	}
}
	