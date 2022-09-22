package in.nareshIT.hc.config;

import java.util.UUID;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
	
	

	public static void main(String arg[]) {
		String str=UUID.randomUUID().toString().substring(0, 8);
		System.out.println(str);
		
	}
}
