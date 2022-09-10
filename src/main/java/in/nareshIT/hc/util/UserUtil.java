package in.nareshIT.hc.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public interface UserUtil {
	
	public static String genPwd() {
		return UUID
				.randomUUID()
				.toString()
				.replace("-", "")
				.substring(0, 8);
	}

}
