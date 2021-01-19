package markt_study.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import markt_study.answering.Answering;
import markt_study.deployment.Deployment;
import markt_study.questionner.Questionner;

@Repository
public interface AnswRepository  {
	
	/**
	 * DAO method to find a answering by lastname or firstname or id
	 * @param search firstname
	 * @param search1 last name
	 * @param search2 id answering
	 * @return list of answering
	 */
	List<Answering> findByFirstNameContainingOrLastNameContainingOrId(String search,String search1,Long search2);
	
}
