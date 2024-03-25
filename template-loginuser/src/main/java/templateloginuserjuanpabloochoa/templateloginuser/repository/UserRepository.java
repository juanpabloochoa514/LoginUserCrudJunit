package templateloginuserjuanpabloochoa.templateloginuser.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import templateloginuserjuanpabloochoa.templateloginuser.entity.Role;
import templateloginuserjuanpabloochoa.templateloginuser.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//	@Modifying
//	@Query(value = "INSERT INTO user  (name_user, last_name_user, email_user, password_user, role) "+"VALUES (:name, :lastName, :email, :password, :role)", nativeQuery = true)
//	void userRegister(@Param("name") String name, @Param("lastName") String lastName, @Param("email") String email, @Param("password") String password, @Param("role")Role role);
	Optional<User> findByUsername(String username);
	
}
