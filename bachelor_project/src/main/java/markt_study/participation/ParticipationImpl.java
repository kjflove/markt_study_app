package markt_study.participation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import markt_study.AbstractService;
import markt_study.answering.Answering;
import markt_study.dao.ParticipateCustum;
import markt_study.dao.ParticipationRepository;
import markt_study.study.Study;


@Service
public class ParticipationImpl extends AbstractService<Participation, Long> implements ParticipationRepository {
	@Autowired
	private ParticipateCustum participateCustum;  
	@Override
	protected JpaRepository<Participation, Long> getRepository() {
	     return participateCustum;
	}
	/**
	 * DAO method to find Participation by Study
	 * @param studyId study id
	 * @return return list of Participation
	 */
	public List<Participation> findByStudy(Study studyId) {
		List<Participation> absences=participateCustum.findByStudy(studyId);
		return absences;
	}
	
	/**
	 * DAO method to find Participation by Answering
	 * @param ans Answering
	 * @return return list of Answering
	 */
	public List<Participation> findByAnswering(Answering ans) {
		List<Participation> absences=participateCustum.findByAnswering(ans);
		return absences;
	}
}
