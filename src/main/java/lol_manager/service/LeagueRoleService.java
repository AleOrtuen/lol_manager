package lol_manager.service;

import lol_manager.dto.LeagueRoleDTO;
import lol_manager.mapper.MapperManager;
import lol_manager.model.LeagueRole;
import lol_manager.repository.LeagueRoleRepository;
import lol_manager.validation.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueRoleService {
    
    @Autowired
    private LeagueRoleRepository leagueRoleRepository;

    @Autowired
    private ChampService champService;

    public LeagueRoleDTO save(LeagueRoleDTO leagueRoleDto) throws Exception {
        Assert.isTrue(Validations.isValidLeagueRole(leagueRoleDto), "Invalid league role form");
        LeagueRole leagueRole = MapperManager.LEAGUEROLEMAPPER.entityFromDto(leagueRoleDto);
        champService.findById(leagueRoleDto.getChamp().getIdChamp());
        return MapperManager.LEAGUEROLEMAPPER.dtoFromEntity(leagueRoleRepository.save(leagueRole));
    }

    public LeagueRoleDTO update(LeagueRoleDTO leagueRoleDto) throws Exception {
        Assert.isTrue(Validations.isValidLeagueRole(leagueRoleDto), "Invalid league role form");
        findById(leagueRoleDto.getIdRole());
        champService.findById(leagueRoleDto.getChamp().getIdChamp());
        LeagueRole leagueRole = MapperManager.LEAGUEROLEMAPPER.entityFromDto(leagueRoleDto);
        return MapperManager.LEAGUEROLEMAPPER.dtoFromEntity(leagueRoleRepository.save(leagueRole));
    }

    public void delete(Long idRole) throws Exception {
        findById(idRole);
        leagueRoleRepository.deleteById(idRole);
    }

    public List<LeagueRoleDTO> findAll() throws Exception {
        List<LeagueRole> leagueRoles = leagueRoleRepository.findAll();
        Assert.isTrue(leagueRoles.size() != 0, "League roles not found");
        return MapperManager.LEAGUEROLEMAPPER.dtoFromEntity(leagueRoles);
    }

    public LeagueRoleDTO findById(Long idRole) throws Exception {
        Optional<LeagueRole> leagueRole = leagueRoleRepository.findById(idRole);
        Assert.isTrue(leagueRole.isPresent(), "League role not found");
        return MapperManager.LEAGUEROLEMAPPER.dtoFromEntity(leagueRole.get());
    }
}
