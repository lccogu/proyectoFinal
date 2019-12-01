package com.clinica.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


import com.clinica.model.entity.Especialidad;
import com.clinica.service.DoctorService;
import com.clinica.service.EspecialidadService;

@Controller
@RequestMapping("/especialidad")
@SessionAttributes({"especialidad"})

public class EspecialidadController {
	@Autowired
	EspecialidadService especialidadService;

	@Autowired
	DoctorService doctorService;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Especialidad> especialidades = especialidadService.findAll();
			model.addAttribute("especialidades", especialidades);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/especialidad/inicio";
		
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Especialidad> optional = especialidadService.findById(id);
			if(optional.isPresent()){
				
				
				model.addAttribute("especialidad", optional.get());
				
				
			}else {
				return "redirect:/especialidad";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return"especialidad/edit";
		
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("especialidad")Especialidad especialidad,
			Model model, SessionStatus status) {
		
		try {
			especialidadService.save(especialidad);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/especialidad";
	}
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Especialidad especialidad = new Especialidad();
		model.addAttribute("especialidad",especialidad);
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "especialidad/nuevo";

	}
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Especialidad> especialidad = especialidadService.findById(id);
			if(especialidad.isPresent()) {
				especialidadService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "Error - Violacion contra el principio de integridad");
			try {
				List<Especialidad> especialidad = especialidadService.findAll();
				model.addAttribute("especialidad",especialidad);
			} catch (Exception e2) {}
				return "especialidad/inicio";
			}
			return "redirect:/especialidad";
		}
	
	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model) {
		try {
			Optional<Especialidad>especialidad = especialidadService.findById(id);
			if(especialidad.isPresent()) {
				model.addAttribute("especialidad",especialidad.get());
			}else {
				return "redirect:/especialidad";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/especialidad/info";
		
	}
	}


