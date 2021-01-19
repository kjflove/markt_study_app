package markt_study.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import markt_study.report.Report;

public interface ReportCustum extends JpaRepository<Report, Long>, ReportRepository{

}
