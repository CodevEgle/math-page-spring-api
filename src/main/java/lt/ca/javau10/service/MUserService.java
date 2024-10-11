package lt.ca.javau10.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lt.ca.javau10.entities.MUser;
import lt.ca.javau10.models.UserDto;
import lt.ca.javau10.repositories.MUserRepository;
import lt.ca.javau10.utils.EntityMapper;

@Service
public class MUserService implements UserDetailsService {

	private final MUserRepository userRepository;
	private final EntityMapper entityMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(MUserService.class);

	public MUserService(MUserRepository userRepository, EntityMapper entityMapper) {
		this.userRepository = userRepository;
		this.entityMapper = entityMapper;
	}
	
	public UserDto createUser(UserDto userDto) {
		MUser userEntityBeforeSave = entityMapper.toUserEntity(userDto);
		MUser userEntityAfterSave = userRepository.save(userEntityBeforeSave);		
		
		return entityMapper.toUserDto(userEntityAfterSave);		
	}
	
	public List<UserDto> getAllUsers(){
		List<MUser> users = userRepository.findAll();
		
		return users.stream()
				.map(entityMapper::toUserDto)
				.toList();		
	}
	public Optional<UserDto> getUserById(Long id) {
		Optional<MUser> user = userRepository.findById(id);
		return user.map(entityMapper::toUserDto);
	}
	
	public Optional<UserDto> updateUser(Long id, UserDto userDto ){
		
		if( userRepository.existsById(id) ) {
			MUser userEntityBeforeSave = entityMapper.toUserEntity(userDto);
			userEntityBeforeSave.setId(id);
			
			MUser userEntityAfterSave = userRepository.save(userEntityBeforeSave);
			return Optional.of( entityMapper.toUserDto(userEntityAfterSave));
			
		} else {
			return Optional.empty();
		}
		
	}
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MUser user = userRepository
				.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		logger.info("Loaded :" + user.toString());
		return entityMapper.toUserDto(user);
	}

}
