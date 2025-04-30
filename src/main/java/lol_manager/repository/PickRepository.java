package lol_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lol_manager.model.Pick;

public interface PickRepository extends JpaRepository<Pick, Long> {

}
