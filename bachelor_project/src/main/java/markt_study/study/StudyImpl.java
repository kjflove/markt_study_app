package markt_study.study;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import markt_study.AbstractService;
import markt_study.answering.Answering;
import markt_study.dao.AnswRepository;
import markt_study.dao.StudyCustum;
import markt_study.dao.StudyRepository;


@Service
public class StudyImpl extends AbstractService<Study, Long> implements StudyRepository {
	@Autowired
	private StudyCustum studyCustum;
	
	@Override
	protected JpaRepository<Study, Long> getRepository() {
	     return studyCustum;
	}
	
    @Override
	public List<Study> findByTyp(String typ) {
		List<Study> study=studyCustum.findByTyp(typ);
		return study;
	}


	@Override
	public List<Study> findByStudyNameContainingOrStudyIdContaining(String search, String search2) {
		List<Study> study=studyCustum.findByStudyNameContainingOrStudyIdContaining(search, search2);
		return study;
	}
}
