package com.clinica;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clinica.model.entity.Cita;
import com.clinica.model.entity.Clinica;
import com.clinica.model.entity.Doctor;
import com.clinica.model.entity.Paciente;
import com.clinica.model.entity.Proveedor;
import com.clinica.model.entity.ResultadoLaboratorio;
import com.clinica.model.entity.TipoAtencion;
import com.clinica.model.repository.CitaRepository;
import com.clinica.model.repository.ClinicaRepository;
import com.clinica.model.repository.DoctorRepository;
import com.clinica.model.repository.EspecialidadRepository;
import com.clinica.model.repository.HorarioAtencionRepository;
import com.clinica.model.repository.PacienteRepository;
import com.clinica.model.repository.ProveedorRepository;
import com.clinica.model.repository.ResultadoLaboratorioRepository;
import com.clinica.model.repository.TipoAtencionRepository;
import com.clinica.model.entity.Especialidad;
import com.clinica.model.entity.HorarioAtencion;

@SpringBootTest
class TrabajoSowasdApplicationTests {

	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	@Autowired
	EspecialidadRepository especialidadRepository;
	
	@Autowired
	CitaRepository citaRepository;
	
	@Autowired
	ClinicaRepository clinicaRepository;
	
	@Autowired
	ProveedorRepository proveedorRepository;
	
	@Autowired
	HorarioAtencionRepository horarioAtencionRepository;
	
	@Autowired
	ResultadoLaboratorioRepository resultadoLaboratorioRepository;
	
	@Autowired
	TipoAtencionRepository tipoAtencionRepository;
	
	@Test
	void contextLoads() {
		try {
		
			ResultadoLaboratorio Finalizado = new ResultadoLaboratorio();
			Finalizado.setDescripcion("Finalizado");
			
			ResultadoLaboratorio Espera = new ResultadoLaboratorio();
			Espera.setDescripcion("En proceso");
			
			Finalizado = resultadoLaboratorioRepository.save(Finalizado);
			Espera = resultadoLaboratorioRepository.save(Espera);
	// Cita
			
			Cita cita1 = new Cita();
			cita1.setFecha("2019, 6, 8");
			cita1.setHora("11 am");
			
			
			
			Cita cita2 = new Cita();
			cita2.setHora("1 pm");
			cita2.setFecha("2019, 6, 8");
			
			cita1 = citaRepository.save(cita1);
			cita2 = citaRepository.save(cita2);
			
			
			// Doctores
			Doctor enrique = new Doctor();
			enrique.setNombre("Enrique Flores");
			enrique.setDireccion("Av brasil 2230");
			enrique.setGenero("masculino");
			enrique.setTelefono(887744589);
			enrique.addCitas(cita1);
			
			
			Doctor luis = new Doctor();
			luis.setNombre("Luis Perez");
			luis.setDireccion("Tomas marsano 1025");
			luis.setGenero("masculino");
			luis.setTelefono(996558221);
			luis.addCitas(cita2);
			
			Doctor lucia = new Doctor();
			lucia.setNombre("Lucia De la Cruz");
			lucia.setDireccion("Av jose larco 1010");
			lucia.setGenero("femenino");
			lucia.setTelefono(547896321);
			lucia.addCitas(cita1);
			
			enrique = doctorRepository.save(enrique);
			luis = doctorRepository.save(luis);
			lucia = doctorRepository.save(lucia);
			
			// Especialidad 
			
			Especialidad pediatra = new Especialidad();
			pediatra .setDescripcion("Pediatra");
			pediatra .addDoctor(luis);
			Especialidad cardiologo = new Especialidad();
			cardiologo.setDescripcion("Cardiologo");
			cardiologo.addDoctor(lucia);
			Especialidad otorrino = new Especialidad();
			otorrino.setDescripcion("Otorrino");
			otorrino.addDoctor(enrique);
			
			pediatra  = especialidadRepository.save(pediatra );
			cardiologo = especialidadRepository.save(cardiologo);
			otorrino = especialidadRepository.save(otorrino);
			

			HorarioAtencion mañana = new HorarioAtencion();
			mañana.setHoras(8);
			mañana.setTipo("mañana");
			mañana.addDoctor(luis);
			mañana.addDoctor(lucia);
			mañana.addDoctor(enrique);
			
			mañana = horarioAtencionRepository.save(mañana);
			
			
			// Clínica
			
			Clinica clinica1 = new Clinica();
			clinica1.setNombre("Clinica 1");
			clinica1.setDireccion("jr independencia 338");
			clinica1.setTelefono(845789);
			clinica1.addDoctor(luis);
			clinica1.addDoctor(lucia);
			clinica1.addDoctor(enrique);
			
			clinica1 = clinicaRepository.save(clinica1);
			
		
			
		
			//Proveedores 
			
			Proveedor prov1 = new Proveedor();
			prov1.setName("Medicinas SAC");
			prov1.setDireccion("Las begonias 2020");
			prov1.setEmail("medicianas@proveedor.com");
			prov1.setTelefono("4444-777");
			
			prov1 = proveedorRepository.save(prov1);
			
			TipoAtencion atencion1 = new TipoAtencion();
			atencion1.setDescripcion("Citacion");
			atencion1.addCita(cita1);
			
			TipoAtencion atencion2 = new TipoAtencion();
			atencion2.setDescripcion("Consulta");
			atencion1.addCita(cita2);
			
			atencion1 = tipoAtencionRepository.save(atencion1);
			atencion2 = tipoAtencionRepository.save(atencion2);
			
			
			// Pacientes
			Paciente juan = new Paciente();
			juan.setNombre("Juan Perez");
			juan.setNacimiento("24/05/1995");
			juan.setTelefono(987887443);
			juan.setGenero("Masculino");
			juan.addCita(cita1);
			
	
			
			
			
			
			Paciente carlos = new Paciente();
			carlos.setNombre("Carlos Sanchez");
			carlos.setNacimiento("24/05/1995");
			carlos.setTelefono(547889665);
			carlos.setGenero("Masculino");
			carlos.addCita(cita2);
			
		
			
			
			Paciente pedro = new Paciente();
			pedro.setNombre("Pedro Gutierrez");
			pedro.setNacimiento("24/05/1995");
			pedro.setTelefono(987887447);
			pedro.setGenero("Masculino");
			pedro.addCita(cita1);
			
			
		
			
			Paciente ximena = new Paciente();
			ximena.setNombre("Ximena Marlith");
			ximena.setNacimiento("24/05/1995");
			ximena.setTelefono(897456123);
			ximena.setGenero("Femenino");
			ximena.addCita(cita2);
			
			
		
			
			juan = pacienteRepository.save(juan);
			carlos = pacienteRepository.save(carlos);
			pedro = pacienteRepository.save(pedro);
			ximena = pacienteRepository.save(ximena);
			
			// Grabar
			pacienteRepository.save(juan);
			pacienteRepository.save(carlos);
			pacienteRepository.save(pedro);
			pacienteRepository.save(ximena);
			doctorRepository.save(enrique);
			doctorRepository.save(luis);
			doctorRepository.save(lucia);
			especialidadRepository.save(pediatra );
			especialidadRepository.save(cardiologo);
			especialidadRepository.save(otorrino);
			clinicaRepository.save(clinica1);
			citaRepository.save(cita1);
			citaRepository.save(cita2);
			proveedorRepository.save(prov1);
			resultadoLaboratorioRepository.save(Finalizado);
			resultadoLaboratorioRepository.save(Espera);
			
			tipoAtencionRepository.save(atencion1);
			tipoAtencionRepository.save(atencion2);
			
			horarioAtencionRepository.save(mañana);
		
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
