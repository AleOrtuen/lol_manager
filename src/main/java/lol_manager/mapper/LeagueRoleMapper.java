package lol_manager.mapper;

import lol_manager.dto.LeagueRoleDTO;
import lol_manager.model.LeagueRole;

public class LeagueRoleMapper extends  BasicMapper<LeagueRole, LeagueRoleDTO> {

    @Override
    public LeagueRole entityFromDto(LeagueRoleDTO dto) throws Exception {
        LeagueRole entity = new LeagueRole();
        entity.setIdRole(dto.getIdRole());
        entity.setChamp(MapperManager.CHAMPMAPPER.entityFromDto(dto.getChamp()));
        entity.setRole(dto.getRole());
        return entity;
    }

    @Override
    public LeagueRoleDTO dtoFromEntity(LeagueRole entity) throws Exception {
        LeagueRoleDTO dto = new LeagueRoleDTO();
        dto.setIdRole(entity.getIdRole());
        dto.setChamp(MapperManager.CHAMPMAPPER.dtoFromEntity(entity.getChamp()));
        dto.setRole(entity.getRole());
        return dto;
    }
}
