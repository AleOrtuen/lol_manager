package lol_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lol_manager.dto.PickDTO;
import lol_manager.enums.SideSelectionEnum;
import lol_manager.mapper.MapperManager;
import lol_manager.model.Pick;
import lol_manager.repository.PickRepository;
import lol_manager.validation.Validations;

@Service
public class PickService {

	@Autowired
	private PickRepository pickRepository;
	
	@Autowired 
	private DraftService draftService;
	
	@Autowired
	private ChampService champService;
	
	public PickDTO save(PickDTO pickDto) throws Exception {
		Assert.isTrue(Validations.isValidPick(pickDto), "Invalid pick form");
		draftService.findById(pickDto.getDraft().getIdDraft());
		champService.findById(pickDto.getPick().getIdChamp());
		Pick pick = MapperManager.PICKMAPPER.entityFromDto(pickDto);
		return MapperManager.PICKMAPPER.dtoFromEntity(pickRepository.save(pick));
	}
	
	public PickDTO update(PickDTO pickDto) throws Exception {
		Assert.isTrue(Validations.isValidPick(pickDto), "Invalid pick form");
		findById(pickDto.getIdPick());
		draftService.findById(pickDto.getDraft().getIdDraft());
		champService.findById(pickDto.getPick().getIdChamp());
		Pick pick = MapperManager.PICKMAPPER.entityFromDto(pickDto);
		return MapperManager.PICKMAPPER.dtoFromEntity(pickRepository.save(pick));
	}
	
	public void delete(Long idPick) throws Exception {
		findById(idPick);
		pickRepository.deleteById(idPick);
	}
	
	public List<PickDTO> findAll() throws Exception {
		List<Pick> picks = pickRepository.findAll();
		Assert.isTrue(picks.size() != 0, "Picks not found");
		return MapperManager.PICKMAPPER.dtoFromEntity(picks);
	}
	
	public PickDTO findById(Long idPick) throws Exception {
		Optional<Pick> pick = pickRepository.findById(idPick);
		Assert.isTrue(pick.isPresent(), "Pick not found");
		return MapperManager.PICKMAPPER.dtoFromEntity(pick.get());
	}
	
	public List<PickDTO> findByIdDraft(Long idDraft) throws Exception {
		draftService.findById(idDraft);
		List<Pick> picks = pickRepository.findByDraftIdDraft(idDraft);
		Assert.isTrue(picks.size() != 0 , "No picks found");
		return MapperManager.PICKMAPPER.dtoFromEntity(picks);
	}
	 
	public List<PickDTO> findBySide(String side) throws Exception {
		Assert.isTrue(SideSelectionEnum.valueOf(side) != null , "Invalid side");
		List<Pick> picks = pickRepository.findBySide(side);
		Assert.isTrue(picks.size() != 0 , "No picks found");
		return MapperManager.PICKMAPPER.dtoFromEntity(picks);
	}
	
	public List<PickDTO> findByIdChamp(Long idChamp) throws Exception {
		champService.findById(idChamp);
		List<Pick> picks = pickRepository.findByPickIdChamp(idChamp);
		Assert.isTrue(picks.size() != 0 , "No picks found");
		return MapperManager.PICKMAPPER.dtoFromEntity(picks);
	}
}
