package lol_manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jakarta.transaction.Transactional;
import lol_manager.dto.ChampRoleDTO;
import lol_manager.mapper.MapperManager;
import lol_manager.model.ChampRole;
import lol_manager.repository.ChampRoleRepository;
import lol_manager.utility.ChampRoleUtility;
import lol_manager.validation.Validations;

@Service
public class ChampRoleService {

	@Autowired
	private ChampRoleRepository champRoleRepository;
	
	@Autowired
	private TeamCompService teamCompService;
	
	@Autowired
	private ChampService champService;
	
	public ChampRoleDTO save(ChampRoleDTO c) throws Exception {
		Assert.isTrue(Validations.validChampRole(c), "Invalid form");
		teamCompService.findById(c.getComp().getIdComp());
		champService.findById(c.getChampion().getIdChamp());
		ChampRole champRole = MapperManager.CHAMPROLEMAPPER.entityFromDto(c);
		return MapperManager.CHAMPROLEMAPPER.dtoFromEntity(champRoleRepository.save(champRole));
	}
	
	@Transactional
	public ChampRoleDTO update(ChampRoleDTO c) throws Exception {
		Assert.isTrue(Validations.validChampRole(c), "Invalid form");
		ChampRole entity = champRoleRepository.findById(c.getChampion().getIdChamp(), c.getComp().getIdComp(), c.getRole());
		Assert.isTrue(entity != null, "Champ role not found");
		entity.setDescr(c.getDescr());
		entity.setPowerPick(c.isPowerPick());
		return MapperManager.CHAMPROLEMAPPER.dtoFromEntity(champRoleRepository.save(entity));
	}
	
	@Transactional
	public void delete(Long idComp, Long idChamp, String role) throws Exception {
		findById(idComp, idChamp, role);
		champRoleRepository.deleteById(idComp, idChamp, role);
	}
	
	public ChampRoleDTO findById(Long idComp, Long idChamp, String role) throws Exception {
		ChampRole champRole = champRoleRepository.findById(idChamp, idComp, role);
		Assert.isTrue(champRole != null, "Champ role not found");
		return MapperManager.CHAMPROLEMAPPER.dtoFromEntity(champRole);
	}
	
	public List<ChampRoleDTO> findByIdComp(Long idComp) throws Exception {
		List<ChampRole> champRoles = champRoleRepository.findByIdChampRole_idComp(idComp);
		Assert.isTrue(champRoles.size() != 0, "Champ roles not found");
		return MapperManager.CHAMPROLEMAPPER.dtoFromEntity(champRoles);
	}
	
	public List<ChampRoleDTO> findByIdChamp(Long idChamp) throws Exception {
		List<ChampRole> champRoles = champRoleRepository.findByIdChampRole_idChamp(idChamp);
		Assert.isTrue(champRoles.size() != 0, "Champ roles not found");
		return MapperManager.CHAMPROLEMAPPER.dtoFromEntity(champRoles);
	}
	
	public List<ChampRoleDTO> findByRole(String role) throws Exception {
		List<ChampRole> champRoles = champRoleRepository.findByIdChampRole_role(role);
		Assert.isTrue(champRoles.size() != 0, "Champ roles not found");
		return MapperManager.CHAMPROLEMAPPER.dtoFromEntity(champRoles);
	}
	
	public List<ChampRoleDTO> findAllCompatible(List<ChampRoleDTO> champRoles) throws Exception {
		List<ChampRole> entity = MapperManager.CHAMPROLEMAPPER.entityFromDto(champRoles);
		List<Long> idsComp = ChampRoleUtility.validIdComps(entity);
		List<Long> invalidChamps = ChampRoleUtility.invalidChamps(entity);
		List<String> invalidRoles = ChampRoleUtility.invalidRoles(entity);
		List<ChampRole> compatibleChamps = champRoleRepository.findAllCompatible(idsComp, invalidRoles, invalidChamps);
		return MapperManager.CHAMPROLEMAPPER.dtoFromEntity(compatibleChamps);
	}
	
	public List<ChampRoleDTO> findAll() throws Exception {
		List<ChampRole> champRoles = champRoleRepository.findAll();
		Assert.isTrue(champRoles.size() != 0 , "No champ roles found");
		return MapperManager.CHAMPROLEMAPPER.dtoFromEntity(champRoles);
	}
}
