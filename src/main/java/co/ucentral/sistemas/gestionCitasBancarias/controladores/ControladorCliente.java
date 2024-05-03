package co.ucentral.sistemas.gestionCitasBancarias.controladores;

import co.ucentral.sistemas.gestionCitasBancarias.dto.CitaDto;
import co.ucentral.sistemas.gestionCitasBancarias.dto.ClienteDto;
import co.ucentral.sistemas.gestionCitasBancarias.servicios.ServicioCliente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorCliente {
    @Autowired
    ServicioCliente servicioCliente;
    @GetMapping("/banco/inicio-sesion")
    public String formularioIngreso(Model model){
        ClienteDto cliente = new ClienteDto();
        model.addAttribute("cliente",cliente);
        return "formulario-ingreso";
    }

    //este metodo permite mostrar el formulario de registro
    @GetMapping("/banco/ingresar")
    public String formularioRegistro(Model model){
        ClienteDto cliente = new ClienteDto();
        model.addAttribute("cliente",cliente);
        return "formulario-registro";
    }

    //falta retornar correctamente la pagona html en caso de que el cliente se pueda registrar por el mometo retorna el inicio
    @PostMapping("/banco/registrar")
    public String registrarCliente(@ModelAttribute("cliente") ClienteDto cliente){
        if(servicioCliente.inicioSesion(cliente)){
            System.out.println("Inicio de sesion Exitoso");
            return "formulario-registro";
        }
        return "index";
    }

    @GetMapping("/cliente/solicitar_cita")
    public String formSolicitarCita(Model model){
        CitaDto cita = new CitaDto();
        model.addAttribute("cita",cita);
        return "solicitar-cita";
    }

    @PostMapping("/cliente/cita")
    public String asignarCita(){


    }





}