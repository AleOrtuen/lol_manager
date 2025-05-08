package lol_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lol_manager.model.GameRoom;

public interface GameRoomRepository extends JpaRepository<GameRoom, String> {

	public GameRoom findByGameIdGame(Long idGame);
}
