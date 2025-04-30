package lol_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lol_manager.model.Draft;

public interface DraftRepository extends JpaRepository<Draft, Long> {

}
