package markt_study.login;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import markt_study.AbstractModel;

@Entity
@Table(name = "roles")
public class Role extends AbstractModel<Long>{
    /*@Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;*/
    
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<>();

   

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


   
      

}
