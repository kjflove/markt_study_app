package markt_study.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import markt_study.questionner.Questionner;

public interface QuestCustum extends JpaRepository<Questionner, Long>, QuestionnerRepository{

}
