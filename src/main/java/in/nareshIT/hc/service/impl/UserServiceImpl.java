package in.nareshIT.hc.service.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.nareshIT.hc.entity.User;
import in.nareshIT.hc.repository.UserRepository;
import in.nareshIT.hc.service.IUserService;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public Long save (User user) {
		String pwd=user.getPassword();
		String enPwd=encoder.encode(pwd);
		user.setPassword(enPwd);
		return repo.save(user).getId();
	}

	@Override
	public Optional<User> findByUserName(String username) {
		return repo.findByuserName(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=findByUserName(username);
		if(!user.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		else {
			User op=user.get();
			return new org.springframework.security.core.userdetails.User(username,
					op.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority(op.getRole())));
		}
	}
}
