package markt_study.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import markt_study.answering.Answering;

public interface AnswCustum  extends JpaRepository<Answering, Long> , AnswRepository{
	
	
	

}
