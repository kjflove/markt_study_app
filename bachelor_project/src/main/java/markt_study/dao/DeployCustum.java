package markt_study.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import markt_study.deployment.Deployment;

public interface DeployCustum extends JpaRepository<Deployment, Long> , DeploymentRepository{

}
