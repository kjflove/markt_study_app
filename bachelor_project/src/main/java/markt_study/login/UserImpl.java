package markt_study.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import markt_study.AbstractService;
import markt_study.answering.Answering;
import markt_study.dao.UserRepository;
import markt_study.login.User;
import markt_study.study.Study;

@Service
public class UserImpl extends AbstractService<User , Long>  {
	@Autowired
	private UserRepository userRepository;  
	@Override
	protected JpaRepository<User, Long> getRepository() {
	     return userRepository;
	}
	
}
