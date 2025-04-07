package lol_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lol_manager.model.Team;
import lol_manager.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
	
	@Modifying
	@Query("UPDATE User u SET u.admin = :admin WHERE u.email = :email")
	public int adminPermit(@Param("admin") boolean admin, @Param("email") String email);
	
    @Query("SELECT t FROM Team t " +
            "JOIN TeamMember tm ON t.idTeam = tm.team.idTeam " +
            "WHERE tm.user.idUser = :userId")
     List<Team> findTeams(Long userId);
    
    @Modifying
    @Query("UPDATE User u "
            + "SET u.username = :username, u.email = :email, u.admin = :admin, u.pRole = :pRole "
            + "WHERE u.idUser = :idUser")
    public int updateNoPassword(@Param("username") String username,
                                  @Param("email") String email,
                                  @Param("admin") boolean admin,
                                  @Param("pRole") String pRole,
                                  @Param("idUser") Long idUser);

}
