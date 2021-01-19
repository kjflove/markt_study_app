package markt_study.questionner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import markt_study.AbstractService;
import markt_study.answering.Answering;
import markt_study.dao.QuestCustum;
import markt_study.dao.QuestionnerRepository;

@Service
public class QuestImpl extends AbstractService<Questionner, Long> implements QuestionnerRepository {
	@Autowired
	private QuestCustum questCustum;
	@Override
	protected JpaRepository<Questionner, Long> getRepository() {
	     return questCustum;
	}
	
	
	public List<Questionner> findByFirstNameContainingOrQuestionnerIdContaining(String search,String search1) {
		List<Questionner> absences=questCustum.findByFirstNameContainingOrQuestionnerIdContaining(search,search1);
		return absences;
	}
}
