package markt_study.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import markt_study.answering.Answering;
import markt_study.client.Client;

@Repository
public interface ClientRepository {
	
	/**
	 * DAO method to find a client by name
	 * @param search name of client
	 * @return list of client
	 */
	List<Client> findByNameContaining(String search);
	
	/**
	 * DAO method to find client by branch
	 * @param branch branch of activity
	 * @return list of client
	 */
	List<Client> findByBranch(String branch);
	
}
