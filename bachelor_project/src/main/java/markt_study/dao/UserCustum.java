package markt_study.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import markt_study.login.User;

public interface UserCustum extends JpaRepository<User, Long>, UserRepository{

}
