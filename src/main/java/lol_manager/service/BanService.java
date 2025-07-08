package lol_manager.service;

import java.util.List;
import java.util.Optional;

import lol_manager.dto.PickDTO;
import lol_manager.model.Pick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lol_manager.dto.BanDTO;
import lol_manager.enums.SideSelectionEnum;
import lol_manager.mapper.MapperManager;
import lol_manager.model.Ban;
import lol_manager.repository.BanRepository;
import lol_manager.validation.Validations;

@Service
public class BanService {

	@Autowired
	private BanRepository banRepository;
	
	@Autowired 
	private DraftService draftService;
	
	@Autowired
	private ChampService champService;

	@Autowired
	private TeamService teamService;
	
	public BanDTO save(BanDTO banDto) throws Exception {
		Assert.isTrue(Validations.isValidBan(banDto), "Invalid ban form");
		draftService.findById(banDto.getDraft().getIdDraft());
		champService.findById(banDto.getBan().getIdChamp());
		Ban ban = MapperManager.BANMAPPER.entityFromDto(banDto);
		return MapperManager.BANMAPPER.dtoFromEntity(banRepository.save(ban));
	}
	
	public BanDTO update(BanDTO banDto) throws Exception {
		Assert.isTrue(Validations.isValidBan(banDto), "Invalid ban form");
		findById(banDto.getIdBan());
		draftService.findById(banDto.getDraft().getIdDraft());
		champService.findById(banDto.getBan().getIdChamp());
		Ban ban = MapperManager.BANMAPPER.entityFromDto(banDto);
		return MapperManager.BANMAPPER.dtoFromEntity(banRepository.save(ban));
	}
	
	public void delete(Long idBan) throws Exception {
		findById(idBan);
		banRepository.deleteById(idBan);
	}
	
	public List<BanDTO> findAll() throws Exception {
		List<Ban> bans = banRepository.findAll();
		Assert.isTrue(bans.size() != 0, "Bans not found");
		return MapperManager.BANMAPPER.dtoFromEntity(bans);
	}
	
	public BanDTO findById(Long idBan) throws Exception {
		Optional<Ban> ban = banRepository.findById(idBan);
		Assert.isTrue(ban.isPresent(), "Ban not found");
		return MapperManager.BANMAPPER.dtoFromEntity(ban.get());
	}
	
	public List<BanDTO> findByIdDraft(Long idDraft) throws Exception {
		draftService.findById(idDraft);
		List<Ban> bans = banRepository.findByDraftIdDraft(idDraft);
		Assert.isTrue(bans.size() != 0 , "No bans found");
		return MapperManager.BANMAPPER.dtoFromEntity(bans);
	}
	 
	public List<BanDTO> findBySide(String side) throws Exception {
		Assert.isTrue(SideSelectionEnum.valueOf(side) != null , "Invalid side");
		List<Ban> bans = banRepository.findBySide(side);
		Assert.isTrue(bans.size() != 0 , "No bans found");
		return MapperManager.BANMAPPER.dtoFromEntity(bans);
	}
	
	public List<BanDTO> findByIdChamp(Long idChamp) throws Exception {
		champService.findById(idChamp);
		List<Ban> bans = banRepository.findByBanIdChamp(idChamp);
		Assert.isTrue(bans.size() != 0 , "No bans found");
		return MapperManager.BANMAPPER.dtoFromEntity(bans);
	}

	public List<BanDTO> findByIdTeam(Long idTeam) throws Exception {
		teamService.findById(idTeam);
		List<Ban> bans = banRepository.findAllBansByIdTeam(idTeam);
		Assert.isTrue(bans.size() != 0 , "No bans found");
		return MapperManager.BANMAPPER.dtoFromEntity(bans);
	}
}
