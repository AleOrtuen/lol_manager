package lol_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lol_manager.model.Ban;

public interface BanRepository extends JpaRepository<Ban, Long> {

	public List<Ban> findByDraftIdDraft(Long idDraft);
	
	public List<Ban> findBySide(String side);
	
	public List<Ban> findByBanIdChamp(Long idChamp);
	
}
