package lol_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lol_manager.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
	
	@Modifying
	@Query("UPDATE User u SET u.admin = :admin WHERE u.email = :email")
	public int adminPermit(@Param("admin") boolean admin, @Param("email") String email);
	
}
