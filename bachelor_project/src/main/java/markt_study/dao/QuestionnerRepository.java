package markt_study.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import markt_study.questionner.Questionner;

@Repository
public interface QuestionnerRepository{
	
	/**
	 * DAO method to find Questioner by firstname or questioner id
	 * @param search firstname
	 * @param search1 questioner id
	 * @return
	 */
	List<Questionner> findByFirstNameContainingOrQuestionnerIdContaining(String search,String search1);
	
}
