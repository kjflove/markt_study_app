package markt_study.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import markt_study.login.Role;

public interface RoleCustum extends JpaRepository<Role, Long>, RoleRepository{

}
