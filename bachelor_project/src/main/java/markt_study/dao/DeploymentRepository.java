package markt_study.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import markt_study.deployment.Deployment;
import markt_study.questionner.Questionner;
import markt_study.study.Study;

@Repository
public interface DeploymentRepository {

	/**
	 * DAO method to find Deployment by Study
	 * @param study object Study
	 * @return list of Deployment
	 */
	List<Deployment> findByStudy(Study study);
	
	/**
	 * DAO method to find deployment by Questioner 
	 * @param quest questioner id
	 * @return list of Deployment
	 */
	List<Deployment> findByQuestionner(Questionner quest);
	
	/**
	 * DAO method to find Deployment by Study and Questioner
	 * @param studyId study id
	 * @param quest questioner id
	 * @return  return list of Deployment
	 */
	List<Deployment> findByStudyAndQuestionner(Study studyId,Questionner quest);

}
