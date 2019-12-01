package com.clinica.init;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clinica.model.entity.Usuario;
import com.clinica.model.repository.AuthorityRepository;
import com.clinica.model.repository.UsuarioRepository;

@Service
public class InitDB implements CommandLineRunner{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		this.usuarioRepository.deleteAll();
		this.authorityRepository.deleteAll();
		
		Usuario admin = new Usuario();
		admin.setUsername("admin");
		admin.setPassword(passwordEncoder.encode("admin"));
		admin.setApellidos("admin");
		admin.setNombres("administrador");
		admin.setCargo("admin");
		admin.setEnable(true);
		
		Usuario enrique = new Usuario();
		enrique.setUsername("eflorez");
		enrique.setPassword(passwordEncoder.encode("Eflorez"));
		enrique.setApellidos("Flores");
		enrique.setNombres("Enrique");
		enrique.setCargo("medico");
		enrique.setEnable(true);
		
		Usuario carmen = new Usuario();
		carmen.setUsername("Carmen");
		carmen.setPassword(passwordEncoder.encode("usuario1"));
		carmen.setApellidos("Ruiz");
		carmen.setNombres("carmen");
		carmen.setCargo("Usuario");
		carmen.setEnable(true);
		
		
		Usuario Maria = new Usuario();
		Maria.setUsername("Maria");
		Maria.setPassword(passwordEncoder.encode("enfermera"));
		Maria.setApellidos("Pineda");
		Maria.setNombres("Maria");
		Maria.setCargo("Enfermera");
		Maria.setEnable(true);
		
		Usuario Pedro = new Usuario();
		Pedro.setUsername("Pedro");
		Pedro.setPassword(passwordEncoder.encode("pedro"));
		Pedro.setApellidos("Miranda");
		Pedro.setNombres("Pedro");
		Pedro.setCargo("Supervisor");
		Pedro.setEnable(true);

		
		admin.addAuthority("ROLE_USER");
		admin.addAuthority("ROLE_ADMIN");
		enrique.addAuthority("ACCESS_MEDICO_READ");
		enrique.addAuthority("ROLE_USER");
		carmen.addAuthority("ROLE_USER");
		carmen.addAuthority("ACCESS_CITA");
		Maria.addAuthority("ROLE_ENFERMERA");
		Maria.addAuthority("");
		Pedro.addAuthority("ROLE_SUPERVISOR");
		Pedro.addAuthority("");
		
		List<Usuario> usuarios = Arrays.asList(admin,enrique,carmen,Maria,Pedro);
		this.usuarioRepository.saveAll(usuarios);
		
		
	}
	

}
