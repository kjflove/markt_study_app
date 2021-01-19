package markt_study.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import markt_study.AbstractService;
import markt_study.dao.RoleRepository;

@Service

public class RoleImpl extends AbstractService<Role , Long>  {
	@Autowired
	private RoleRepository roleRepository;  
	@Override
	protected JpaRepository<Role, Long> getRepository() {
	     return roleRepository;
	}

}
