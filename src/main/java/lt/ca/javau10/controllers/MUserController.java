package lt.ca.javau10.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau10.models.Role;
import lt.ca.javau10.models.UserDto;
import lt.ca.javau10.services.MUserService;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class MUserController {
	
	private final MUserService userService;

	public MUserController(MUserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/")
	public ResponseEntity<UserDto>  createUser(@RequestBody UserDto userDto) {
		UserDto createdUser = userService.createUser(userDto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);	
	}
	
	@GetMapping("/")
    public String getPublicContent() {
        return "Public Content";
    }

    // Endpoint accessible only by authenticated users with ROLE_USER or higher
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String getUserBoard() {
        return "User Board";
    }

    // Endpoint accessible only by users with ROLE_MODERATOR
    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String getModeratorBoard() {
        return "Moderator Board";
    }
	@GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminBoard() {
        return "Admin Board";
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id){	
		Optional<UserDto> userInBox = userService.getUserById(id);
		return userInBox
				.map( ResponseEntity::ok )
				.orElseGet( () -> ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(
										@PathVariable Long id, 
										@RequestBody UserDto userDto){
		Optional<UserDto> userInBox = userService.updateUser(id, userDto);
		
		return userInBox
				.map( ResponseEntity::ok )
				.orElseGet( () -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping ("/{userId}/roles")
    @PreAuthorize("hasRole('ADMIN')")
	public Set<Role> addRoleToUser(@PathVariable Long userId, @RequestBody Role role) {
		return userService.addRoleTuUser(userId, role);
	}
	
	@DeleteMapping("/{userId}/roles")
    @PreAuthorize("hasRole('ADMIN')")
	public Set<Role> deleteRoleFromUser(@PathVariable Long userId, @RequestBody Role role){
		return userService.deleteRoleFromUser(userId, role);
	}
}
