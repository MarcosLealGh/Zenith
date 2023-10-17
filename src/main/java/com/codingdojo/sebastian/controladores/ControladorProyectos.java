	package com.codingdojo.sebastian.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.sebastian.modelos.Proyecto;
import com.codingdojo.sebastian.modelos.Usuario;
import com.codingdojo.sebastian.servicios.Servicio;
import com.codingdojo.sebastian.servicios.ServicioProyectos;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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

		//*----Verificar usuario en sesion----*//
        Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
        if(usuarioTemporal == null) {
            return "redirect:/";
        }
        //*----Verificar usuario en sesion----*//
        
        //Lista de todos los proyectos
        List<Proyecto> todosLosProyectos = sp.listaTodosLosProyectos();
        //Lista de proyectos del usuario
        List<Proyecto> proyectosDelUsuario = sp.listaProyectoPorUsuario(usuarioTemporal);

        model.addAttribute("todosLosProyectos", todosLosProyectos);
        model.addAttribute("proyectosDelUsuario", proyectosDelUsuario);


        return "dashboard.jsp";
    }
	
	@GetMapping("/crear/proyectos")
	public String crearProyectos(HttpSession session ,@ModelAttribute("nuevoProyecto") Proyecto nuevoProyecto ) {
		
		//*----Verificar usuario en sesion----*//
        Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
        if(usuarioTemporal == null) {
            return "redirect:/";
        }
        //*----Verificar usuario en sesion----*//
        
        return "formularioproyecto.jsp";
		
	}
	
	@PostMapping("/crearProyecto")
	public String crearProyecto(HttpSession session,
								@Valid @ModelAttribute("nuevoProyecto") Proyecto nuevoProyecto,
								Model model,BindingResult result) {
		//*----Verificar usuario en sesion---*//
        Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
        if(usuarioTemporal == null) {
            return "redirect:/";
        }
        //*----Verificar usuario en sesion----*//
        if(result.hasErrors()) {
            
            List<Proyecto> proyectos = sp.listaTodosLosProyectos();
            model.addAttribute("proyectos", proyectos);

            return "dashboard.jsp";
        } else {
        
        	// guardar proyecto nuevo
        	sp.crearProyectos(nuevoProyecto);

        	// Obtener la lista de proyectos actualizada
        	List<Proyecto> proyectos = sp.listaTodosLosProyectos();

        	// Agregar la lista actualizada de proyectos al modelo
        	model.addAttribute("proyectos", proyectos);

        	return "redirect:/dashboard"; 
        }
	}

	@GetMapping("/proyectos")
	public String proyectos(HttpSession session) {
		
		 //*----Verificar usuario en sesion----*//
        Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
        if(usuarioTemporal == null) {
            return "redirect:/";
        }
        //*----Verificar usuario en sesion----*//
		return "proyects.jsp";
	}
	
	
	
	
}
