package lol_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lol_manager.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
