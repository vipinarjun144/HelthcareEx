package in.nareshIT.hc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshIT.hc.entity.Patient;
import in.nareshIT.hc.exception.PatientNotFoundException;
import in.nareshIT.hc.service.IPatientService;

@Controller
@RequestMapping("/patient")
@Transactional
public class PatientController {

	@Autowired
	private IPatientService service;

	//1 show reg page
	@GetMapping("/register")
	public String showRegPage(@RequestParam(value ="massage",required = false) String massage,
			Model model) {
		model.addAttribute("massage", massage);
		return"PatientRegister";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute Patient patient,RedirectAttributes model) {
		Long id=service.savePatient(patient);
		model.addAttribute("massage", "Patient ("+id+") is created");
		return "redirect:register";
	}

	@GetMapping("/all")
	public String getAll(@RequestParam(value="massage",required = false)String massage,
			Model model) {
		List<Patient> list=service.getAllPatient();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>"+list.get(0).getMedicalHistory());
		model.addAttribute("list", list);
		model.addAttribute("massage", massage);
		return "PatientData";
	}

	@GetMapping("/delete")
	public String deletePatient(@RequestParam("id") Long id,
			RedirectAttributes attribute) {

		String massage=null;
		try {
			service.removePatient(id);
			massage="Patient removed";
		}catch(PatientNotFoundException e) {
			e.printStackTrace();
			massage="Oops!! Not Found";
		}
		attribute.addAttribute("massage", massage);
		return "redirect:all";
	}

	@GetMapping("/edit")
	public String editeDoctor(@RequestParam("id") Long id,
			RedirectAttributes attribute,Model model) {

		String page=null;
		try {
			Patient patient=service.getOnePatient(id);
			model.addAttribute("patient", patient);
			page="PatientEdit";
		}catch(PatientNotFoundException e) {
			e.printStackTrace();
			attribute.addAttribute("massage", e.getMessage());
			page="redirect:all";
		}
		return page;
	}

	@PostMapping("/update")
	public String updateDoctor(@ModelAttribute Patient patient,
			RedirectAttributes attribute) {
		service.updatePatient(patient);
		String massage="Patient"+ patient.getId()+ "Updated";
		attribute.addAttribute("massage",massage);
		return "redirect:all";
	}

}
