package lt.ca.javau10.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated
	private ERole name;

	public Role() {}
	
	public Role(ERole name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name.toString();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}
