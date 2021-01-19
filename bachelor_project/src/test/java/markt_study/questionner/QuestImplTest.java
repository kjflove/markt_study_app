package markt_study.questionner;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import markt_study.dao.QuestCustum;

class QuestImplTest {
	

	@Mock
	private QuestCustum questCusturm;
	 @InjectMocks
	private QuestImpl questImpl;
	Questionner quest;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		quest=new Questionner();
		quest.setQuestionnerId("QUE00001");
		quest.setFirstName("Tom");
	}

	@Test
	final void testFindByFirstNameContainingOrQuestionnerIdContaining() {
		List<Questionner> list= new ArrayList<Questionner>();
		list.add(quest);
	when(questCusturm.findByFirstNameContainingOrQuestionnerIdContaining("tom","QUE00001")).thenReturn(list);
	List<Questionner> questtest=questImpl.findByFirstNameContainingOrQuestionnerIdContaining("tom","QUE00001");
		assertEquals(questtest.get(0).getFirstName(),quest.getFirstName());
		assertEquals(questtest.get(0).getQuestionnerId(),quest.getQuestionnerId());


	}

	@Test
	final void testSave() {
		when(questCusturm.save(quest)).thenReturn(quest);
		Questionner savedQuest=questImpl.save(quest);
		assertEquals(savedQuest.getFirstName(),quest.getFirstName());
		assertEquals(savedQuest.getQuestionnerId(),quest.getQuestionnerId());

	}

}
