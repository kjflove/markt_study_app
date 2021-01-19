package markt_study.client;

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

import markt_study.dao.ClientCustum;

class ClientImplTest {
	
	@Mock
	private ClientCustum clientCusturm;
	
	 @InjectMocks
	private ClientImpl clientImpl;
	
	Client client;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		client=new Client();
		client.setName("Orange Cameroun");
		client.setBranch("Telephon Mobil");
		
	}

	

	@Test
	final void testSave() {
		when(clientCusturm.save(Mockito.any(Client.class))).thenReturn(client);
		Client clien=clientImpl.save(client);
		assertEquals(client.getName(),clien.getName());
	}
	
	@Test
	final void testFindByNameContaining() {
		List<Client> list= new ArrayList<Client>();
		list.add(client);
		when(clientCusturm.findByNameContaining("Orange Cameroun")).thenReturn(list);
	    List<Client> clienttest=clientImpl.findByNameContaining("Orange Cameroun");
		assertEquals(clienttest.get(0).getName(),"Orange Cameroun");
		
	}

	@Test
	final void testFindByBranch() {
		List<Client> list= new ArrayList<Client>();
		list.add(client);
		when(clientCusturm.findByBranch("Telephon Mobil")).thenReturn(list);
	    List<Client> clienttest=clientImpl.findByBranch("Telephon Mobil");
		assertEquals(clienttest.get(0).getName(),"Orange Cameroun");
	}
		

}
