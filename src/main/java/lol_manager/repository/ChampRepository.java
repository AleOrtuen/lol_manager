package lol_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lol_manager.model.Champion;

public interface ChampRepository extends JpaRepository<Champion, Long> {

	public Champion findByName(String name);
}
