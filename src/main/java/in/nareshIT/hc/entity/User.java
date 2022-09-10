package in.nareshIT.hc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_tab")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="uid_col")
	private Long id;
	
	@Column(name="user_dispay_col")
	private String userDisplay;
	
	@Column(name="username_col")
	private String userName;
	
	@Column(name="password_col")
	private String password;
	
	@Column(name="role_col")
	private String role;
	

}
