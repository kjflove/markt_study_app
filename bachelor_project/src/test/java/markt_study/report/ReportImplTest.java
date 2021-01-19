package markt_study.report;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import markt_study.dao.ReportCustum;
import markt_study.study.Study;

class ReportImplTest {
	
	@Mock
	private ReportCustum reportCusturm;
	
	 @InjectMocks
	private ReportImpl reportImpl;
	Report report;
	Study study;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        report=new Report();
        study=new Study();
        study.setStudyId("ST000001");
        report.setName("resume call");
        report.setStudyId(study);
	}

	@Test
	final void testFindByStudyIdContaining() {
	
		List<Report> list= new ArrayList<Report>();
		list.add(report);
	when(reportCusturm.findByStudyIdContaining(study.getStudyId())).thenReturn(list);
	List<Report> reporttest=reportImpl.findByStudyIdContaining("ST000001");
		assertEquals(reporttest.get(0).getName(),report.getName());
		assertEquals(reporttest.get(0).getStudyId().getStudyId(),report.getStudyId().getStudyId());

	}

	@Test
	final void testSave() {
		when(reportCusturm.save(report)).thenReturn(report);
		Report savedReport=reportImpl.save(report);
		assertEquals(savedReport.getName(),report.getName());
		assertEquals(savedReport.getStudyId().getStudyId(),report.getStudyId().getStudyId());
	}

}
