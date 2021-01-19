package markt_study.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import markt_study.participation.Participation;

public interface ParticipateCustum extends JpaRepository<Participation, Long>, ParticipationRepository{

}
