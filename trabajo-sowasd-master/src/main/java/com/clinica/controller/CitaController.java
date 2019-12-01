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
import org.springframework.web.bind.support.SessionStatus;

import com.clinica.model.entity.Cita;
import com.clinica.model.entity.Doctor;
import com.clinica.model.entity.Paciente;
import com.clinica.model.entity.TipoAtencion;
import com.clinica.service.CitaService;
import com.clinica.service.DoctorService;
import com.clinica.service.PacienteService;
import com.clinica.service.TipoAtencionService;

@Controller
@RequestMapping("/cita")
public class CitaController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private CitaService citaService;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private TipoAtencionService tipoAtencionService;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Cita> citas = citaService.findAll();
			model.addAttribute("citas",citas);
		}catch(Exception e) {}
		return "cita/inicio";
	}
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Cita> optional = citaService.findById(id);	
			if(optional.isPresent()) {
				List <Doctor> doctores = doctorService.findAll();
				model.addAttribute("doctores", doctores);	
				List <Paciente> pacientes = pacienteService.findAll();
				model.addAttribute("pacientes", pacientes);	
				List <TipoAtencion> tipoAtenciones = tipoAtencionService.findAll();
				model.addAttribute("tipoAtenciones", tipoAtenciones);	
				model.addAttribute("cita", optional.get());
				
			} else {
				return "redirect:/cita";
			}
		}catch(Exception e) {
			
		}
		return "cita/edit";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("cita") Cita cita,
			Model model, SessionStatus status) {
		try {
			citaService.save(cita);
			status.setComplete();
		}catch(Exception e) {
			
		}
		
		return "redirect:/cita";
	}
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Cita cita = new Cita();
		model.addAttribute("cita", cita);
		try {
			List <Doctor> doctores = doctorService.findAll();
			model.addAttribute("doctores", doctores);	
			List <Paciente> pacientes = pacienteService.findAll();
			model.addAttribute("pacientes", pacientes);	
			List <TipoAtencion> tipoAtenciones = tipoAtencionService.findAll();
			model.addAttribute("tipoAtenciones", tipoAtenciones);	
			
		} catch(Exception e) {
			
		}
		return "cita/nuevo";
	}
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Cita> cita = citaService.findById(id);
			if(cita.isPresent()) {
				citaService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "Error - Violacion contra el principio de integridad");
			try {
				List<Cita> citas = citaService.findAll();
				model.addAttribute("citas", citas);
			} catch(Exception e2) {}
			return "cita/inicio";
		}
		return "redirect:/cita";
	}
	
	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model) {
		try {
			Optional<Cita> cita = citaService.findById(id);
			if(cita.isPresent()) {
				model.addAttribute("cita", cita.get());
			} else {
				return "redirect:/cita";
			}
		} catch (Exception e) {

		}	
		
		return "/cita/info";
	}
	
}
