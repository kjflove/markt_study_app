package markt_study.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import markt_study.AbstractService;
import markt_study.dao.ReportRepository;
import markt_study.report.Report;
import markt_study.study.Study;


@Service
public class ReportImpl extends AbstractService<Report, Long>  {
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Override
	protected JpaRepository<Report, Long> getRepository() {
		// TODO Auto-generated method stub
		return reportRepository;
	}
	
	/**
	 * DAO method to find Report by Study
	 * @param search study id
	 * @return list of Report
	 */
	public List<Report> findByStudyIdContaining(String search) {
		List<Report> report=reportRepository.findByStudyIdContaining(search);
		return report;
	}

}
