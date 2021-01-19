package markt_study.deployment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import markt_study.AbstractService;
import markt_study.dao.DeployCustum;
import markt_study.dao.DeploymentRepository;
import markt_study.questionner.Questionner;
import markt_study.study.Study;

@Service
public class DeployImpl extends AbstractService<Deployment, Long> implements DeploymentRepository {
	@Autowired
	private DeployCustum deployCustum ;  

	
	

     @Override
     protected JpaRepository<Deployment, Long> getRepository() {
	// TODO Auto-generated method stub
	return deployCustum;
}
    @Override
	public List<Deployment> findByStudy(Study studyId) {
		List<Deployment> absences=deployCustum.findByStudy(studyId);
		return absences;
	}
	
   @Override
	public List<Deployment> findByQuestionner(Questionner questId) {
		List<Deployment> absences=deployCustum.findByQuestionner(questId);
		return absences;
	}
	
   @Override
	public List<Deployment> findByStudyAndQuestionner(Study studyId,Questionner questId) {
		List<Deployment> absences=deployCustum.findByStudyAndQuestionner(studyId,questId);
		return absences;
	}


}
