package com.clinica.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.model.entity.Doctor;
import com.clinica.model.entity.Paciente;
import com.clinica.service.DoctorService;
import com.clinica.service.PacienteService;


@RestController
@RequestMapping("/api/doctores")
public class DoctorResController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< List<Doctor> > fetchDoctores() {
		try {
			List<Doctor> doctores = doctorService.findAll();
			return new ResponseEntity<List<Doctor>>(doctores, HttpStatus.OK);   
		} catch (Exception e) {
			return new ResponseEntity<List<Doctor>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< Doctor > saveDoctor(@RequestBody Doctor doctor) {
		try {
			Doctor newDoctor = doctorService.save(doctor);
			return new ResponseEntity< Doctor >(newDoctor, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity< Doctor >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< Doctor > updateDoctor(@PathVariable("id") Integer id, 
			@RequestBody Doctor doctor) {
		try {
			if(id.equals(doctor.getId())) {
				Optional<Doctor> optional = doctorService.findById(id);
				if(optional.isPresent()) {
					Doctor updateDoctor = doctorService.update(doctor);
					return new ResponseEntity<Doctor>(updateDoctor, 
							HttpStatus.OK);
				} else {
					return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
				}				
			} else {
				return new ResponseEntity<Doctor>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Doctor>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Doctor> deleteDoctor(@PathVariable("id") Integer id) {
		try {			
			Optional<Doctor> optional = doctorService.findById(id);
			if(optional.isPresent()) {
				doctorService.deleteById(id);
				return new ResponseEntity<Doctor>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Doctor>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

















