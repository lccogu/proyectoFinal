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

import com.clinica.model.entity.Producto;
import com.clinica.model.entity.Proveedor;
import com.clinica.service.ProductoService;
import com.clinica.service.ProveedorService;

@Controller
@RequestMapping("/producto")
@SessionAttributes({"producto"})
public class ProductoController {

	@Autowired
	ProductoService productoService;
	
	@Autowired
	ProveedorService proveedorService;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Producto> productos = productoService.findAll();
			model.addAttribute("productos",productos);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/producto/inicio";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Producto> optional = productoService.findById(id);
			if(optional.isPresent()) {
				List<Proveedor> proveedores = proveedorService.findAll();
				model.addAttribute("producto", optional.get());
				model.addAttribute("proveedores",proveedores);
			}else {
				return "redirect:/producto";

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "producto/edit";

	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("producto")Producto producto, 
			Model model, SessionStatus status) {
		
		try {
			productoService.save(producto);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/producto";

	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Producto producto = new Producto();
		model.addAttribute("producto",producto);
		try {
			List<Proveedor> proveedores = proveedorService.findAll();
			model.addAttribute("proveedores",proveedores);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "producto/nuevo";
	}
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Producto> producto = productoService.findById(id);
			if(producto.isPresent()) {
				productoService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "Error - Violacion contra el principio de integridad");
			try {
				List<Producto> productos = productoService.findAll();
				model.addAttribute("productos",productos);
			} catch (Exception e2) {}
                     return	 "productos/inicio";		
		}
		return "redirect:/producto";
	}
	
	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model) {
		
		try {
			Optional<Producto> producto = productoService.findById(id);
			if(producto.isPresent()) {
				model.addAttribute("producto",producto.get());
			}else {
				return "redirect:/producto";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/producto/info";
	}
}
