package in.nareshIT.hc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshIT.hc.entity.Doctor;
import in.nareshIT.hc.exception.DoctorNotFoundExceptions;
import in.nareshIT.hc.service.IDoctorService;
import in.nareshIT.hc.service.ISpecializationService;
import in.nareshIT.hc.util.MymailUtil;

@Controller
@RequestMapping("/doc")
public class DoctorController {
	
	@Autowired
	private MymailUtil mail;
	
	@Autowired
	private IDoctorService service;
	
	@Autowired
	private ISpecializationService specService;
	
	
	public void createDynamicUI(Model model) {
		model.addAttribute("specialzations", specService.getIdAndSpecName());
	} 
	
	//1 show reg page
	@GetMapping("/register")
	public String showRegPage(@RequestParam(value ="massage",required = false) String massage,
			Model model) {
		model.addAttribute("massage", massage);
		createDynamicUI(model);
		return"DoctorRegister";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute Doctor doc,RedirectAttributes model) {
		Long id=service.saveDoctor(doc);
		model.addAttribute("massage", "Doctor ("+id+") is created");
		
		//mail.send(doc.getEmail(), "Doctorpdf ", "You are successfully Register");
		//mail.send(doc.getEmail(),"Doctorpdf ", "just download", new ClassPathResource("/static/myRes/Doctor.pdf"));
		System.out.println("Sending mail>>>>>>>>>>>>>>>>>>>>>>>>>");
		return "redirect:register";
	}
	
	@GetMapping("/all")
	public String getAll(@RequestParam(value="massage",required = false)String massage,
			Model model) {
		List<Doctor> list=service.getAllDoctor();
		System.out.println(list);
		model.addAttribute("list", list);
		model.addAttribute("massage", massage);
		return "DoctorData";
	}
	
	@GetMapping("/delete")
	public String deleteDoctor(@RequestParam("id") Long id,
			RedirectAttributes attribute) {
		
		String massage=null;
		try {
		service.removeDoctor(id);
		massage="Doctor removed";
		}catch(DoctorNotFoundExceptions e) {
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
		Doctor doctor=service.getOneDoctor(id);
		model.addAttribute("doctor", doctor);
		createDynamicUI(model);
		page="DoctorEdit";
		}catch(DoctorNotFoundExceptions e) {
			e.printStackTrace();
			attribute.addAttribute("massage", e.getMessage());
			page="redirect:all";
		}
	   return page;
	}
	
	@PostMapping("/update")
	public String updateDoctor(@ModelAttribute Doctor doc,
			RedirectAttributes attribute) {
		    service.updateDoctor(doc);
		    String massage="Doctor"+ doc.getId()+ "Updated";
		     attribute.addAttribute("massage",massage);
	         return "redirect:all";
	       }
}
