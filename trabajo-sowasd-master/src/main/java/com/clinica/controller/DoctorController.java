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

import com.clinica.model.entity.Clinica;
import com.clinica.model.entity.Doctor;
import com.clinica.model.entity.Especialidad;
import com.clinica.model.entity.HorarioAtencion;

import com.clinica.service.ClinicaService;
import com.clinica.service.DoctorService;
import com.clinica.service.EspecialidadService;
import com.clinica.service.HorarioAtencionService;







@Controller
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private EspecialidadService especialidadService;
	
	@Autowired
	private HorarioAtencionService horarioAtencionService;
	
	@Autowired
	private ClinicaService clinicaService;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Doctor> doctores = doctorService.findAll();
			model.addAttribute("doctores",doctores);
		}catch(Exception e) {}
		return "doctor/inicio";
	}
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Doctor> optional = doctorService.findById(id);	
			if(optional.isPresent()) {
				List <Especialidad> especialidades = especialidadService.findAll();
				model.addAttribute("especialidades", especialidades);	
				List <HorarioAtencion> horarioAtenciones = horarioAtencionService.findAll();
				model.addAttribute("horarioAtenciones", horarioAtenciones);	
				List <Clinica> clinicas = clinicaService.findAll();
				model.addAttribute("clinicas", clinicas);	
				model.addAttribute("doctor", optional.get());
				
			} else {
				return "redirect:/doctor";
			}
		}catch(Exception e) {
			
		}
		return "doctor/edit";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("doctor") Doctor doctor,
			Model model, SessionStatus status) {
		try {
			doctorService.save(doctor);
			status.setComplete();
		}catch(Exception e) {
			
		}
		
		return "redirect:/doctor";
	}
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Doctor doctor = new Doctor();
		model.addAttribute("doctor", doctor);
		try {
			List<Especialidad> especialidades = especialidadService.findAll();
			model.addAttribute("especialidades", especialidades);
			List<HorarioAtencion> horarioAtenciones = horarioAtencionService.findAll();
			model.addAttribute("horarioAtenciones", horarioAtenciones);
			List<Clinica> clinicas = clinicaService.findAll();
			model.addAttribute("clinicas", clinicas);
			
		} catch(Exception e) {
			
		}
		return "doctor/nuevo";
	}
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Doctor> doctor = doctorService.findById(id);
			if(doctor.isPresent()) {
				doctorService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "Error - Violacion contra el principio de integridad");
			try {
				List<Doctor> doctores = doctorService.findAll();
				model.addAttribute("doctores", doctores);
			} catch(Exception e2) {}
			return "doctor/inicio";
		}
		return "redirect:/doctor";
	}
	
	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model) {
		try {
			Optional<Doctor> doctor = doctorService.findById(id);
			if(doctor.isPresent()) {
				model.addAttribute("doctor", doctor.get());
			} else {
				return "redirect:/doctor";
			}
		} catch (Exception e) {

		}	
		
		return "/doctor/info";
	}
	
}