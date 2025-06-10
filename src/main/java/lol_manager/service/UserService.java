package lol_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jakarta.transaction.Transactional;
import lol_manager.dto.TeamDTO;
import lol_manager.dto.UserDTO;
import lol_manager.mapper.MapperManager;
import lol_manager.model.Team;
import lol_manager.model.User;
import lol_manager.repository.UserRepository;
import lol_manager.validation.Validations;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
	
	public UserDTO save(UserDTO u) throws Exception {
		Assert.isTrue(Validations.isValidFormUser(u), "Invalid form");
		Assert.isTrue(userRepository.findByEmail(u.getEmail()) == null, "Email registered");
		User user = MapperManager.USERMAPPER.entityFromDto(u);
		user.setPassword(encoder.encode(user.getPassword()));
		return MapperManager.USERMAPPER.dtoFromEntity(userRepository.save(user));
	}
	
	@Transactional
	public UserDTO update(UserDTO u) throws Exception {
		findById(u.getIdUser());
		User user = MapperManager.USERMAPPER.entityFromDto(u);
		User entity = new User();
		if (u.getPassword() == null) {
			Assert.isTrue(Validations.isValidFormNoPassword(u), "Invalid form");
			int update = userRepository.updateNoPassword(
					user.getUsername(), 
					user.getEmail(), 
					user.isAdmin(), 
					user.getpRole(), 
					user.getIdUser()
			);
			if (update > 0) {
				entity = MapperManager.USERMAPPER.entityFromDto(findById(user.getIdUser()));
			};
		} else {
			Assert.isTrue(Validations.isValidFormUser(u), "Invalid form");
			user.setPassword(encoder.encode(u.getPassword()));
			entity = userRepository.save(user);
		}
		return MapperManager.USERMAPPER.dtoFromEntity(entity);
	}
	
	public void delete(Long idUser) throws Exception {
		findById(idUser);
		userRepository.deleteById(idUser);
	}
	
	@Transactional
	public UserDTO adminPermit(String email) throws Exception {
		UserDTO user = findByEmail(email);
		user.setAdmin(!user.isAdmin());
		int modifiche = userRepository.adminPermit(user.isAdmin(), user.getEmail());	
		return modifiche > 0 ? user : null;
	}
	
	public UserDTO authUser(String email, String password) throws Exception {
		User user = userRepository.findByEmail(email);
		Assert.isTrue(user != null, "User not found");
		Assert.isTrue(encoder.matches(password, user.getPassword()), "Invalid credentials");
		return MapperManager.USERMAPPER.dtoFromEntity(user);
	}
	
	public List<UserDTO> findAll() throws Exception {
		List<User> users = userRepository.findAll();
		Assert.isTrue(users.size() != 0 , "No users found");
		return MapperManager.USERMAPPER.dtoFromEntity(users);
	}
	
	public UserDTO findById(Long idUser) throws Exception {
		Optional<User> user = userRepository.findById(idUser);
		Assert.isTrue(user.isPresent(), "User not found");
		return MapperManager.USERMAPPER.dtoFromEntity(user.get());
	}
	
	public UserDTO findByEmail(String email) throws Exception {
		User user = userRepository.findByEmail(email);
		Assert.isTrue(user != null, "User not found");
		return MapperManager.USERMAPPER.dtoFromEntity(user);
	}
	
	public List<TeamDTO> findTeams(Long idUser) throws Exception {
		findById(idUser);
		List<Team> teams = userRepository.findTeams(idUser);
		Assert.isTrue(teams.size() != 0, "Teams not found");
		return MapperManager.TEAMMAPPER.dtoFromEntity(teams);
	}
	
}
