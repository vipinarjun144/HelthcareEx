package in.nareshIT.hc.controller;

import java.util.List;
import java.util.Map;

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
import in.nareshIT.hc.entity.Doctor;
import in.nareshIT.hc.service.IAppointmentSerice;
import in.nareshIT.hc.service.IDoctorService;
import in.nareshIT.hc.service.ISpecializationService;

@Controller
@RequestMapping("/appoint")
public class AppointmentController {

	@Autowired
	private IAppointmentSerice service;

	@Autowired
	private IDoctorService docService;

	@Autowired
	private ISpecializationService speService;

	public void createDynamicUI(Model model) {
		model.addAttribute("doctors", docService.getIdAndDocName());

	}

	@GetMapping("/register")
	public String showReg(@RequestParam(value = "massage", required = false) String massage, Model model) {
		model.addAttribute("massage", massage);
		createDynamicUI(model);
		return "AppointmentRegister";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute Appointment appointment, RedirectAttributes attribute) {
		Long id = service.saveAppointment(appointment);
		System.out.println(">>>>>>>>>>>>>>>." + appointment.getDoctor().getId());
		String massage = "Appointment saved with id :" + id;
		attribute.addAttribute("massage", massage);
		return "redirect:register";
	}

	@GetMapping("/view")
	public String ViewSlote(@RequestParam(required = false, defaultValue = "0") Long specId, Model model) {
		System.out.println("specId : " + specId);
		Map<Long, String> speName = speService.getIdAndSpecName();
		model.addAttribute("specializations", speName);

		List<Doctor> doc = null;
		String massage = null;
		if (specId <= 0) {// if they not select any spec id
			doc = docService.getAllDoctor();
			massage = "Result : All Doctors";
		} else {
			doc = docService.findDoctorBySpecName(specId);
			massage = speService.getOneSpecialization(specId).getSpecName();
		}
		model.addAttribute("massage", massage);
		model.addAttribute("doc", doc);
		return "AppointmentSearch";
	}
}
