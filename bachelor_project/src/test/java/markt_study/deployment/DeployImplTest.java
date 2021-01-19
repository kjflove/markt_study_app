package markt_study.deployment;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import markt_study.dao.DeployCustum;
import markt_study.questionner.Questionner;
import markt_study.study.Study;

class DeployImplTest {
	
	@Mock
	private DeployCustum deployCusturm;
	
	 @InjectMocks
	private DeployImpl deployImpl;
	 
	 Study study;
	 Questionner quest;
	 Deployment deploy;
	 
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		study=new Study();
		study.setStudyId("ST00001");
		quest=new Questionner();
		quest.setQuestionnerId("QUE00001");
		deploy=new Deployment();
		deploy.setStudy(study);
		deploy.setQuestionner(quest);
	}

	@Test
	final void testFindByStudy() {
		List<Deployment> list= new ArrayList<Deployment>();
		list.add(deploy);
		when(deployCusturm.findByStudy(study)).thenReturn(list);
		List<Deployment> absences=deployImpl.findByStudy(study);
		assertEquals(absences.get(0).getStudy().getStudyId(),"ST00001");
	}

	@Test
	final void testFindByQuestionner() {
		List<Deployment> list= new ArrayList<Deployment>();
		list.add(deploy);
		when(deployCusturm.findByQuestionner(quest)).thenReturn(list);
		List<Deployment> absences=deployImpl.findByQuestionner(quest);
		assertEquals(absences.get(0).getQuestionner().getQuestionnerId(),"QUE00001");
		
	}

	@Test
	final void testFindByStudyAndQuestionner() {
		List<Deployment> list= new ArrayList<Deployment>();
		list.add(deploy);
		when(deployCusturm.findByStudyAndQuestionner(study,quest)).thenReturn(list);
		List<Deployment> absences=deployImpl.findByStudyAndQuestionner(study,quest);
		assertEquals(absences.get(0).getQuestionner().getQuestionnerId(),"QUE00001");
		assertEquals(absences.get(0).getStudy().getStudyId(),"ST00001");
	}

	@Test
	final void testSave() {
		List<Deployment> list= new ArrayList<Deployment>();
		list.add(deploy);
		when(deployCusturm.save(deploy)).thenReturn(deploy);
		Deployment savedDeploy=deployImpl.save(deploy);
		assertEquals(savedDeploy.getStudy().getStudyId(),"ST00001");
	}

}
