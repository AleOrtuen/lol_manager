package lol_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lol_manager.model.ChampPool;
import lol_manager.model.embedded.ChampPoolEmbedded;

public interface ChampPoolRepository extends JpaRepository<ChampPool, ChampPoolEmbedded> {

}
