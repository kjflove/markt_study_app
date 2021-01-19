package markt_study.client;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import markt_study.AbstractService;
import markt_study.dao.ClientCustum;
import markt_study.dao.ClientRepository;

@Service
public class ClientImpl extends AbstractService<Client, Long> implements ClientRepository {
	@Autowired
	private ClientCustum clientCustum;
	
	@Override
	protected JpaRepository<Client, Long> getRepository() {
	     return clientCustum;
	}
	
	@Override
	public List<Client> findByNameContaining(String search) {
		List<Client> cli=clientCustum.findByNameContaining(search);
		return cli;
	}
	
	@Override
	public List<Client> findByBranch(String search) {
		List<Client> cli=clientCustum.findByBranch(search);
		return cli;
	}
}
