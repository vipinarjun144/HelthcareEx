package in.nareshIT.hc.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.ui.Model;

public class SystemDate {
	
	public String date(Model model) {
		model.addAttribute("standardDate", new Date());
		model.addAttribute("localDateTime", LocalDateTime.now());
		model.addAttribute("localDate", LocalDate.now());
		return "commonui";
		
	}

}
