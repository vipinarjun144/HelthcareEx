package in.nareshIT.hc.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public interface UserUtil {
	
	public static String genPwd() {
		String pwd=UUID
				.randomUUID()
				.toString()
				.substring(0, 8);
		return pwd;
	}

}
