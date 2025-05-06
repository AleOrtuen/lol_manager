package lol_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lol_manager.model.Pick;

public interface PickRepository extends JpaRepository<Pick, Long> {

	public List<Pick> findByDraftIdDraft(Long idDraft);
	
	public List<Pick> findBySide(String side);
	
	public List<Pick> findByPickIdChamp(Long idChamp);
	
}
