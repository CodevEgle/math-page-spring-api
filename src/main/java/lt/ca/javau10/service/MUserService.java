package lt.ca.javau10.service;

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
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MUser user = userRepository
				.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		logger.info("Loaded :" + user.toString());
		return entityMapper.toUserDto(user);
	}

}
