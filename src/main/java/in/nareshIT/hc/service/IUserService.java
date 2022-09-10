package in.nareshIT.hc.service;

import java.util.Optional;

import in.nareshIT.hc.entity.User;

public interface IUserService {
	
	Long save(User user);
	
	Optional<User> findByUserName(String username);

}
