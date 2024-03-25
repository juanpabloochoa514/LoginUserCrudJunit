package templateloginuserjuanpabloochoa.templateloginuser.entity;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Entity
@Table(name="users")
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	public User() {
		
	}
	public User(String name,String lastName, String username, String password, Role role) {
		this.name=name;
		this.lastName=lastName;
		this.username=username;
		this.password=password;
		this.role=role;
	}
	public User(String username) {
		this.username=username;
	}
	public User(String username, String password) {
		this.username=username;
		this.password=password;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message="name user must not be in blank")
	@Column(name="name_user")
	private String name;
	@NotBlank(message="The surname of user must not be in blank")
	@Column(name="last_name_user")
	private String lastName;
	@Email(message="email invalid, it is blank")
	@Column(name="email_user")
	private String username;
	@NotBlank(message="the password must not be in blank")
	@Column(name="password_user")
	private String password;
	@Enumerated(EnumType.STRING) 
    Role role;
	
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		 return List.of(new SimpleGrantedAuthority((role.name())));
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}
