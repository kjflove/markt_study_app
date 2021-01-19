package markt_study.participation;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import markt_study.answering.Answering;
import markt_study.dao.ParticipateCustum;
import markt_study.study.Study;

class ParticipationImplTest {
	
	
	@Mock
	private ParticipateCustum partCusturm;
	
	 @InjectMocks
	private ParticipationImpl partImpl;
	 
	 Study study;
	 Answering ans;
	 Participation part;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		study=new Study();
		study.setStudyId("ST00001");
		ans=new Answering();
		ans.setFirstName("tom");
		ans.setLastName("jean");
		part=new Participation();
		part.setStudy(study);
		part.setAnswering(ans);
		
	}
	
	@Test
	final void testSave() {
		List<Participation> list= new ArrayList<Participation>();
		list.add(part);
		when(partCusturm.save(part)).thenReturn(part);
		Participation savedpart=partImpl.save(part);
		assertEquals(savedpart.getStudy().getStudyId(),"ST00001");
	}

	@Test
	final void testFindByStudy() {
		List<Participation> list= new ArrayList<Participation>();
		list.add(part);
		when(partCusturm.findByStudy(study)).thenReturn(list);
		List<Participation> findedList=partImpl.findByStudy(study);
		assertEquals(findedList.get(0).getStudy().getStudyId(),"ST00001");
		Mockito.verify(partCusturm).findByStudy(part.getStudy());
	}

	@Test
	final void testFindByAnswering() {
		
		List<Participation> list= new ArrayList<Participation>();
		list.add(part);
		when(partCusturm.findByAnswering(ans)).thenReturn(list);
		List<Participation> findedList=partImpl.findByAnswering(ans);
		assertEquals(findedList.get(0).getAnswering().getFirstName(),"tom");
		Mockito.verify(partCusturm).findByAnswering(part.getAnswering());
		
	}

	

}
