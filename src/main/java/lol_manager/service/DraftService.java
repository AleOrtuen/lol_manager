package lol_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lol_manager.dto.DraftDTO;
import lol_manager.mapper.MapperManager;
import lol_manager.model.Draft;
import lol_manager.repository.DraftRepository;
import lol_manager.validation.Validations;

@Service
public class DraftService {

	@Autowired 
	private DraftRepository draftRepository;
	
	@Autowired
	private GameService gameService;

	@Autowired
	private TeamService teamService;
	
	public DraftDTO save(DraftDTO draftDto) throws Exception {
		Assert.isTrue(Validations.validDraft(draftDto), "Invalid draft form");
		Draft draft = MapperManager.DRAFTMAPPER.entityFromDto(draftDto);
		gameService.findById(draftDto.getGame().getIdGame());
		return MapperManager.DRAFTMAPPER.dtoFromEntity(draftRepository.save(draft));
	}
	
	public DraftDTO update(DraftDTO draftDto) throws Exception {
		findById(draftDto.getIdDraft());
		Assert.isTrue(Validations.validDraft(draftDto), "Invalid draft form");
		Draft draft = MapperManager.DRAFTMAPPER.entityFromDto(draftDto);
		teamService.findById(draft.getTeamBlue().getIdTeam());
		teamService.findById(draft.getTeamRed().getIdTeam());
		return MapperManager.DRAFTMAPPER.dtoFromEntity(draftRepository.save(draft));
	}
	
	public void delete(Long idDraft) throws Exception {
		findById(idDraft);
		draftRepository.deleteById(idDraft);
	}
	
	public List<DraftDTO> findAll() throws Exception {
		List<Draft> drafts = draftRepository.findAll();
		Assert.isTrue(drafts.size() != 0, "Drafts not found");
		return MapperManager.DRAFTMAPPER.dtoFromEntity(drafts);
	}
	
	public DraftDTO findById(Long idDraft) throws Exception {
		Optional<Draft> draft = draftRepository.findById(idDraft);
		Assert.isTrue(draft.isPresent(), "Draft not found");
		return MapperManager.DRAFTMAPPER.dtoFromEntity(draft.get());
	}
	
	public List<DraftDTO> findByIdGame(Long idGame) throws Exception {
		List<Draft> drafts = draftRepository.findByGameIdGame(idGame);
//		Assert.isTrue(draft.isPresent(), "Draft not found");
		return MapperManager.DRAFTMAPPER.dtoFromEntity(drafts);
	}
	
	public List<DraftDTO> findByIdTeam(Long idTeam) throws Exception {
		teamService.findById(idTeam);
		return MapperManager.DRAFTMAPPER.dtoFromEntity(draftRepository.findByIdTeam(idTeam));
	}
	
	public DraftDTO setWinner(DraftDTO draftDto) throws Exception {
		Assert.isTrue(draftDto.getWinner() != null, "No winner idTeam");
		teamService.findById(draftDto.getWinner().getIdTeam());
		return update(draftDto);
	}
}
