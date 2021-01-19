package markt_study.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import markt_study.client.Client;

public interface ClientCustum extends JpaRepository<Client, Long>, ClientRepository{

}
