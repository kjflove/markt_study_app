package markt_study.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import markt_study.login.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
