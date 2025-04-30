package lol_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lol_manager.model.Ban;

public interface BanRepository extends JpaRepository<Ban, Long> {

}
