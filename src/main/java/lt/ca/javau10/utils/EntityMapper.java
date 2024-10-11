package lt.ca.javau10.utils;

import org.springframework.stereotype.Component;

import lt.ca.javau10.entities.MUser;
import lt.ca.javau10.models.UserDto;


@Component
public class EntityMapper {
	
	public MUser toUserEntity(UserDto dto) {
		MUser entity = new MUser();
		entity.setId(dto.getId());
		entity.setUsername(dto.getUsername());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		//entity.setGrades(dto.getGrades());
		//entity.setRoles(dto.getRoles());
		return entity;
	}
	
	public UserDto toUserDto(MUser entity) {
		return new UserDto(
				entity.getId(), 
				entity.getUsername(), 
				entity.getEmail(), 
				entity.getGrades(),
				entity.getRoles(),
				entity.getPassword() 
				);
	}
}
