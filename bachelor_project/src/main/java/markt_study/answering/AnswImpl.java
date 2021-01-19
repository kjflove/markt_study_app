package markt_study.answering;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import markt_study.AbstractService;
import markt_study.dao.AnswCustum;
import markt_study.dao.AnswRepository;

@Service
public class AnswImpl extends AbstractService<Answering, Long> implements AnswRepository{
	@Autowired
	private AnswCustum answCusturm;
	
	@Override
	protected JpaRepository<Answering, Long> getRepository() {
	     return answCusturm;
	}
	
	@Override
	public List<Answering> findByFirstNameContainingOrLastNameContainingOrId(String search,String search1,Long search2) {
		List<Answering> absences=answCusturm.findByFirstNameContainingOrLastNameContainingOrId(search,search1,search2);
		return absences;
	}
	
	
}
