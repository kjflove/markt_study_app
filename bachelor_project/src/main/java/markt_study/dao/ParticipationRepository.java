package markt_study.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import markt_study.answering.Answering;
import markt_study.participation.Participation;
import markt_study.study.Study;

public interface ParticipationRepository  {

	/**
	 * DAO method to find Participation by Study
	 * @param study study
	 * @return list of Participation
	 */
	List<Participation> findByStudy(Study study);
	
	/**
	 * DAO method to find Participation by Answering
	 * @param answering id
	 * @return list of Answering
	 */
	List<Participation> findByAnswering(Answering ans);

}
