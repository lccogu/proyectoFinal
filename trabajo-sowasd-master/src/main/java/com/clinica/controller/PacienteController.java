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


import com.clinica.model.entity.Paciente;

import com.clinica.service.PacienteService;


@Controller
@RequestMapping("/paciente")
@SessionAttributes({"paciente"})
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Paciente> pacientes = pacienteService.findAll();
			model.addAttribute("pacientes", pacientes);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/paciente/inicio";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Paciente> optional = pacienteService.findById(id);	
			if(optional.isPresent()) {
			
				model.addAttribute("paciente", optional.get());	
			} else {
				return "redirect:/paciente";
			}
		}catch(Exception e) {
			
		}
		return "paciente/edit";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("paciente") Paciente paciente,
			Model model, SessionStatus status) {
		try {
			pacienteService.save(paciente);
			status.setComplete();
		}catch(Exception e) {
			
		}
		
		return "redirect:/paciente";
	}
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Paciente paciente = new Paciente();
		model.addAttribute("paciente", paciente);
		try {
			
			
		} catch(Exception e) {
			
		}
		return "paciente/nuevo";
	}
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Paciente> paciente = pacienteService.findById(id);
			if(paciente.isPresent()) {
				pacienteService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "Error - Violacion contra el principio de integridad");
			try {
				List<Paciente> pacientes = pacienteService.findAll();
				model.addAttribute("pacientes", pacientes);
			} catch(Exception e2) {}
			return "pacientes/inicio";
		}
		return "redirect:/paciente";
	}
	
	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model) {
		try {
			Optional<Paciente> paciente = pacienteService.findById(id);
			if(paciente.isPresent()) {
				model.addAttribute("paciente", paciente.get());
			} else {
				return "redirect:/paciente";
			}
		} catch (Exception e) {

		}	
		
		return "/paciente/info";
	}
	
}