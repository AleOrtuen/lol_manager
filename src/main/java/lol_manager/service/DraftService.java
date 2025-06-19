package lol_manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.transaction.Transactional;
import lol_manager.dto.GameRoomDTO;
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

	@Autowired
	private GameRoomService gameRoomService;

	public DraftDTO save(DraftDTO draftDto) throws Exception {
		Assert.isTrue(Validations.isValidDraft(draftDto), "Invalid draft form");
		Draft draft = MapperManager.DRAFTMAPPER.entityFromDto(draftDto);
		gameService.findById(draftDto.getGame().getIdGame());
		if (draftDto.getTeamBlue() != null) {
			teamService.findById(draft.getTeamBlue().getIdTeam());
		}
		if (draftDto.getTeamRed() != null) {
			teamService.findById(draft.getTeamRed().getIdTeam());
		}
		return MapperManager.DRAFTMAPPER.dtoFromEntity(draftRepository.save(draft));
	}
	
	public DraftDTO update(DraftDTO draftDto) throws Exception {
		Assert.isTrue(Validations.isValidDraft(draftDto), "Invalid draft form");
		findById(draftDto.getIdDraft());
		Draft draft = MapperManager.DRAFTMAPPER.entityFromDto(draftDto);
		if (draftDto.getTeamBlue() != null) {
			teamService.findById(draft.getTeamBlue().getIdTeam());
		}
		if (draftDto.getTeamRed() != null) {
			teamService.findById(draft.getTeamRed().getIdTeam());
		}
		return MapperManager.DRAFTMAPPER.dtoFromEntity(draftRepository.save(draft));
	}

	@Transactional
	public DraftDTO setWinner(DraftDTO draftDto) throws Exception {
		Assert.isTrue(draftDto.getWinner() != null, "No winner idTeam");
		teamService.findById(draftDto.getWinner().getIdTeam());
		DraftDTO winner = update(draftDto);
		List<Draft> drafts = draftRepository.findByGameIdGame(draftDto.getGame().getIdGame());
		Map<Long, Integer> wins = new ConcurrentHashMap<>();
		for (Draft draft : drafts) {
			if (draft.getWinner() != null && draft.getWinner().getIdTeam() != null) {
				wins.compute(draft.getWinner().getIdTeam(), (id, count) -> count == null ? 1 : count + 1);
			}
		}
		boolean end = false;
		for (Integer winCount : wins.values()) {
			if (draftDto.getGame().getStyle().equals("bo1")) {
				if(winCount == 1) {
					end = true;
					break;
				}
			}
			if (draftDto.getGame().getStyle().equals("bo3")) {
				if(winCount == 2) {
					end = true;
					break;
				}
			}
			if (draftDto.getGame().getStyle().equals("bo5")) {
				if(winCount == 3) {
					end = true;
					break;
				}
			}
		}

		if (!end) {
			DraftDTO newDraft = new DraftDTO();
			newDraft.setGame(draftDto.getGame());
			save(newDraft);
		}

		return winner;
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
		gameService.findById(idGame);
		List<Draft> drafts = draftRepository.findByGameIdGame(idGame);
		Assert.isTrue(drafts.size() != 0, "Drafts not found");
		return MapperManager.DRAFTMAPPER.dtoFromEntity(drafts);
	}
	
	public List<DraftDTO> findByIdTeam(Long idTeam) throws Exception {
		teamService.findById(idTeam);
		List<Draft> drafts = draftRepository.findByIdTeam(idTeam);
		Assert.isTrue(drafts.size() != 0, "Drafts not found");
		return MapperManager.DRAFTMAPPER.dtoFromEntity(drafts);
	}

	public List<DraftDTO> findDraftByRoomId(String idRoom) throws Exception {
		gameRoomService.findById(idRoom);
		List<Draft> drafts = draftRepository.findDraftByRoomId(idRoom);
		Assert.isTrue(drafts.size() != 0, "Drafts not found");
		return MapperManager.DRAFTMAPPER.dtoFromEntity(drafts);
	}

	public DraftDTO findOpenDraftByRoomId(String idRoom) throws Exception {
		gameRoomService.findById(idRoom);
		Draft draft = draftRepository.findOpenDraftByRoomId(idRoom);
		Assert.isTrue(draft != null, "Draft not found");
		return MapperManager.DRAFTMAPPER.dtoFromEntity(draft);
	}

}
