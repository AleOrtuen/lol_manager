package lol_manager.mapper;

import lol_manager.dto.UserDTO;
import lol_manager.model.User;

public class UserMapper {

	public User entityFromDto(UserDTO dto) {
		User entity = new User();
		entity.setIdUser(dto.getIdUser());
		entity.setUsername(dto.getUsername());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setAdmin(dto.isAdmin());
		return entity;
	}
	
	public UserDTO dtoFromEntity(User entity) {
		UserDTO dto = new UserDTO();
		dto.setIdUser(entity.getIdUser());
		dto.setUsername(entity.getUsername());
		dto.setEmail(entity.getEmail());
		dto.setAdmin(entity.isAdmin());
		return dto;
	}
	
}
