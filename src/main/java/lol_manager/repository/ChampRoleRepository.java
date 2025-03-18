package lol_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lol_manager.model.ChampRole;
import lol_manager.model.embedded.ChampRoleEmbedded;

public interface ChampRoleRepository extends JpaRepository<ChampRole, ChampRoleEmbedded> {
	            
	@Modifying
	@Query("DELETE FROM ChampRole cr WHERE cr.idChampRole.idComp = :idComp AND cr.idChampRole.idChamp = :idChamp AND cr.idChampRole.role = :role")
	void deleteById(@Param("idComp") Long idComp, @Param("idChamp") Long idChamp, @Param("role") String role);
	
    @Query("SELECT cr FROM ChampRole cr WHERE cr.idChampRole.idChamp = :idChamp AND cr.idChampRole.idComp = :idComp AND cr.idChampRole.role = :role")
    ChampRole findById(@Param("idChamp") Long idChamp, @Param("idComp") Long idComp, @Param("role") String role);
    
    List<ChampRole> findByIdChampRole_idComp(Long idComp);
    
    List<ChampRole> findByIdChampRole_idChamp(Long idChamp);
    
    List<ChampRole> findByIdChampRole_role(String role);
    
    @Query("SELECT cr FROM ChampRole cr " +
    		"WHERE cr.idChampRole.idComp IN :idsComp " +
    	    "AND cr.idChampRole.role NOT IN :excludedRoles " +
    		"AND cr.idChampRole.idChamp NOT IN :excludedChampIds")
    List<ChampRole> findAllCompatible(
    	    @Param("idsComp") List<Long> idsComp, 
    	    @Param("roles") List<String> excludedRoles, 
    	    @Param("excludedChampIds") List<Long> excludedChampIds
    );
		
}
