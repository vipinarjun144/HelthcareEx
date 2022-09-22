package in.nareshIT.hc.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.nareshIT.hc.constant.UserRoles;
import in.nareshIT.hc.entity.User;
import in.nareshIT.hc.service.IUserService;
import in.nareshIT.hc.util.UserUtil;


@Component
public class MasterAccountStepUpRunner implements CommandLineRunner{
	
	@Value("${master.user.mail}")
	private String username;
	
	@Value("${master.user.name}")
	private String displayname;
	
	@Autowired
	private IUserService userService;
	
	

	@Override
	public void run(String... args) throws Exception {
		if(!userService.findByUserName(username).isPresent()) {
			String pwd=UserUtil.genPwd();
			User user= new User();
			user.setUserDisplay(displayname);
			user.setUserName(username);
			user.setPassword(pwd);
			user.setRole(UserRoles.ADMIN.name());
			userService.save(user);
			String text="Master username is :"+username+" Passwaord is :"+pwd;
			System.out.println(text);
			//TODO: msil part is remaining
		}
		
		
	}

}
