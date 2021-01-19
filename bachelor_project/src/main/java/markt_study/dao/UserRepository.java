package markt_study.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import markt_study.login.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
	/**
	 * DAO method to get User
	 * @param username
	 * @return return a user
	 */
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
}