package com.codingdojo.sebastian.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.sebastian.modelos.Proyecto;
import com.codingdojo.sebastian.modelos.Usuario;
import com.codingdojo.sebastian.servicios.Servicio;
import com.codingdojo.sebastian.servicios.ServicioProyectos;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorProyectos {

	@Autowired
	private Servicio su;
	
	@Autowired
	private ServicioProyectos sp;
	
	@GetMapping("/dashboard")
    public String dashboard(HttpSession session,
    						 @ModelAttribute("nuevaProyecto") Proyecto NuevoProyecto,
    						 Model model) {

		//Verificar usuario en sesion//
        Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
        if(usuarioTemporal == null) {
            return "redirect:/";
        }
        Usuario miUsuario = su.encontrarUsuario(usuarioTemporal.getId());
        model.addAttribute("usuario", miUsuario);
        //Verificar usuario en sesion//
        
        List<Proyecto> proyectos = sp.listaProyectos();
        model.addAttribute("proyectos", proyectos);

        return "dashboard.jsp";
    }
	
	@PostMapping("/crearProyecto")
	public String crearProyecto(HttpSession session,
								@ModelAttribute("nuevoProyecto") Proyecto nuevoProyecto,
								Model model) {
		//Verificar usuario en sesion//
        Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
        if(usuarioTemporal == null) {
            return "redirect:/";
        }
        Usuario miUsuario = su.encontrarUsuario(usuarioTemporal.getId());
        model.addAttribute("usuario", miUsuario);
        //Verificar usuario en sesion//
        
	    // guardar proyecto nuevo
	    sp.guardarProyecto(nuevoProyecto);

	    // Obtener la lista de proyectos actualizada
	    List<Proyecto> proyectos = sp.listaProyectos();

	    // Agregar la lista actualizada de proyectos al modelo
	    model.addAttribute("proyectos", proyectos);

	    return "dashboard.jsp"; 
	}

	@GetMapping("/proyectos")
	public String proyectos() {
		return "proyects.jsp";
	}
	
}
