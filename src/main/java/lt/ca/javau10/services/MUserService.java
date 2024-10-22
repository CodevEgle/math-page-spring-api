package lt.ca.javau10.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lt.ca.javau10.entities.Grade;
import lt.ca.javau10.entities.MUser;
import lt.ca.javau10.models.ERole;
import lt.ca.javau10.models.GradeDto;
import lt.ca.javau10.models.Role;
import lt.ca.javau10.models.UserDto;
import lt.ca.javau10.repositories.GradeRepository;
import lt.ca.javau10.repositories.MUserRepository;
import lt.ca.javau10.repositories.RoleRepository;
import lt.ca.javau10.repositories.TopicRepository;
import lt.ca.javau10.utils.EntityMapper;

@Service
public class MUserService implements UserDetailsService {

	private final MUserRepository userRepository;
	private final GradeRepository gradeRep;
	private final TopicRepository topicRep;
	private final RoleRepository roleRep;
	private final EntityMapper entityMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(MUserService.class);

	public MUserService(MUserRepository userRepository, EntityMapper entityMapper, GradeRepository gradeRep,
			TopicRepository topicRep, RoleRepository roleRep) {
		this.userRepository = userRepository;
		this.entityMapper = entityMapper;
		this.gradeRep = gradeRep;
		this.topicRep = topicRep;
		this.roleRep = roleRep;
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

	public List<Grade> getAllGradesFromUser(Long userId){
		return userRepository.findById(userId).orElseThrow().getGrades();
	}

	public Optional<Grade> addGradeToUser(/*Long userId,*/ GradeDto gradeDto) {
		if( userRepository.existsById(gradeDto.getMuserId()) ) {
			Grade grade = new Grade();
			MUser user = userRepository.findById(gradeDto.getMuserId()).get();
			grade.setMUser(user);
			grade.setTopic(topicRep.findById(gradeDto.getTopicId()).get());
			grade.setScore(gradeDto.getScore());
			
			List<Grade> grades = user.getGrades();
			grades.add(grade);
			userRepository.save(user);
			//gradeRep.save(grade);
			return Optional.of(grade);
			
		} else {
			return Optional.empty();
		}
	}

	public Set<Role> addRoleTuUser(Long userId, Role role) {
	    MUser user = userRepository.findById(userId)
	        .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

	    Set<Role> roles = user.getRoles();
	    ERole eRole;
	    try {
	        eRole = ERole.valueOf(role.getName());
	    } catch (IllegalArgumentException e) {
	        throw new RuntimeException("Invalid role: " + role);
	    }

	    // Find the role in the database
	    Role roleEntity = roleRep.findByName(eRole)
	        .orElseThrow(() -> new RuntimeException("Role not found: " + role));

	    roles.add(roleEntity);
	    user.setRoles(roles);
	    userRepository.save(user);

	    return user.getRoles();
	}

	public Set<Role> deleteRoleFromUser(Long userId, Role role) {
	    MUser user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
	    Set<Role> roles = user.getRoles();
	    boolean roleRemoved = roles.removeIf(r -> r.getName().equals(role.getName()));
	    if (!roleRemoved) {
	        throw new RuntimeException("Role not found: " + role.getName());
	    }

	    user.setRoles(roles);
	    userRepository.save(user);
	    return user.getRoles();
	}
}
