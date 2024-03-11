package templateloginuserjuanpabloochoa.templateloginuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import templateloginuserjuanpabloochoa.templateloginuser.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value= "SELECT u.email_user FROM users u WHERE u.email_user = :emailUser", nativeQuery = true)
	 User findByUserName(@Param("emailUser") String email);
}
