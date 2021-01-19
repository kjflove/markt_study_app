package markt_study.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import markt_study.study.Study;

public interface StudyCustum extends JpaRepository<Study, Long>, StudyRepository{

}
