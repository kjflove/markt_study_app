package markt_study.answering;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import markt_study.dao.AnswCustum;


class AnswImplTest {

	@Mock
	private AnswCustum answCusturm;
	
	 @InjectMocks
	private AnswImpl answImpl;
	
	Answering ans;
   

	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ans=new Answering();
		ans.setFirstName("jean");
		ans.setLastName("Tom");
		
	}


	@Test
	final void testSave() throws Exception{
		when(answCusturm.save(ans)).thenReturn(ans);
		Answering answr=answImpl.save(ans);
		assertEquals(answr.getFirstName(),ans.getFirstName());
	}

	@Test
	final void testFindByFirstNameContainingOrLastNameContainingOrId() {
		
		List<Answering> list= new ArrayList<Answering>();
		list.add(ans);
	when(answCusturm.findByFirstNameContainingOrLastNameContainingOrId("jean","tom",1L)).thenReturn(list);
	List<Answering> anstest=answImpl.findByFirstNameContainingOrLastNameContainingOrId("jean","tom",1L);
		assertEquals(anstest.get(0).getFirstName(),ans.getFirstName());
	}



}
