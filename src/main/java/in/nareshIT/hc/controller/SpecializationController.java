package in.nareshIT.hc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshIT.hc.entity.Specialization;
import in.nareshIT.hc.exception.SpecializationNotFoundException;
import in.nareshIT.hc.exception.excel.SpecializationExcelDocument;
import in.nareshIT.hc.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {

	@Autowired
	private ISpecializationService service;

	@GetMapping("/register")
	public String displayRegister() {
		return "SpecializationRegister";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute Specialization spec,Model model) {
		Long id=service.saveSpecialization(spec);
		String massage="Record ("+id+") created";
		model.addAttribute("massage", massage);
		return "SpecializationRegister";
	}

	@GetMapping("/all")
	public String viewAll(Model model,
			@RequestParam(value ="massage",required = false) String massage ){
		List<Specialization> list=service.getAllSpecialization();
		model.addAttribute("list", list);
		model.addAttribute("massage", massage);
		return "SpecializationData";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id,
			RedirectAttributes attributes) {
		try {
			service.removeSpecialization(id);
			attributes.addAttribute("massage", "Record ("+id+") is removed!");
		}
		catch(SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("massage", e.getMessage());	
		}
		return "redirect:all";
	}

	@GetMapping("/edit")
	public String showEdite(@RequestParam Long id,Model model,RedirectAttributes attribite) {
	
		String page="";
		try {
		Specialization spec=service.getOneSpecialization(id);
		model.addAttribute("specialization", spec);
		page= "SpecializationEdit";	
		}
		catch(SpecializationNotFoundException e) {
			e.printStackTrace();
			attribite.addAttribute("massage",e.getMessage());
			page="redirect:all";
		}
		return page;
	}


	@PostMapping("/update")
	public String updateSpec(@ModelAttribute Specialization spec,RedirectAttributes attributes) {
		service.updateSpecialization(spec);
		attributes.addAttribute("massage", "Record ("+spec.getId()+") is Update!");
		return "redirect:all";	
	}

	@GetMapping("/checkCode")
	@ResponseBody 
	public String valideSpecCode(@RequestParam String spec) {
		String massage="";
		if(service.isspecCodeExist(spec)) {
			massage =spec+" ,Already Exist!!";
		}

		return massage;

	}
	
	@GetMapping("/excel") 
	public ModelAndView exportToExcel() {
		ModelAndView m=new ModelAndView();
		List<Specialization> list=service.getAllSpecialization();
		m.addObject("list", list);
		m.setView(new SpecializationExcelDocument());
		return m;

	}
	
}
