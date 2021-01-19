package markt_study.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import markt_study.report.Report;
import markt_study.study.Study;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>{
	
	/**
	 * DAO method to find Report by Study
	 * @param search study id
	 * @return list of Report
	 */
	 @Query("select c from Report c join c.studyId a where (a.studyId LIKE %:studyId%)")
	 List<Report> findByStudyIdContaining(@Param("studyId")String search);

}
