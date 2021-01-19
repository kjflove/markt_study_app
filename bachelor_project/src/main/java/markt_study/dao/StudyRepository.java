package markt_study.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import markt_study.answering.Answering;
import markt_study.study.Study;

@Repository
public interface StudyRepository {
    /**
     * DAO method to find Study by name or study id
     * @param search study name
     * @param search2 study id
     * @return list of Study
     */
	List<Study> findByStudyNameContainingOrStudyIdContaining(String search, String search2);
	/**
	 * DAO method to find Study by type
	 * @param typ type of study
	 * @return list of Study
	 */
	List<Study> findByTyp(String typ);
}
