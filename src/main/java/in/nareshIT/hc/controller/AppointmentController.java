package in.nareshIT.hc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshIT.hc.entity.Appointment;
import in.nareshIT.hc.service.IAppointmentSerice;
import in.nareshIT.hc.service.IDoctorService;

@Controller
@RequestMapping("/appoint")
public class AppointmentController {
	
	@Autowired
	private IAppointmentSerice service;
	
	@Autowired
	private IDoctorService docService;
	
	public void createDynamicUI(Model model) {
		model.addAttribute("doctors", docService.getIdAndDocName());
		
	}
	
	
	@GetMapping("/register")
	public String showReg(@RequestParam(value="massage",required = false) String massage,
			Model model) {
		model.addAttribute("massage", massage);
		createDynamicUI(model);
		return "AppointmentRegister";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute Appointment appointment,
			RedirectAttributes attribute) {
		Long id=service.saveAppointment(appointment);
		System.out.println(">>>>>>>>>>>>>>>."+appointment.getDoctor().getId());
		String massage="Appointment saved with id :"+id;
		attribute.addAttribute("massage", massage);
		return "redirect:register";
	}

}
