package co.ucentral.sistemas.gestionCitasBancarias.controladores;

import co.ucentral.sistemas.gestionCitasBancarias.dto.CitaDto;
import co.ucentral.sistemas.gestionCitasBancarias.dto.ClienteDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Sede;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCita;
import co.ucentral.sistemas.gestionCitasBancarias.servicios.ServicioCliente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class ControladorCliente {
    @Autowired
    ServicioCliente servicioCliente;

    @Autowired
    RepoCita repoCita;

    @GetMapping("/banco/inicio-sesion")
    public String formularioIngreso(Model model){
        ClienteDto cliente = new ClienteDto();
        model.addAttribute("cliente",cliente);
        return "formulario-ingreso";
    }

    @PostMapping("/banco/ingresar")
    public String iniciarSecion(@ModelAttribute("cliente") ClienteDto cliente){
        if(servicioCliente.inicioSesion(cliente)){
            //return "inicio-cliente";
            return "index";
        }
        return "redirect:/formulario-ingreso";
    }


    //este metodo permite mostrar el formulario de registro
    @GetMapping("/banco/formulario-registro")
    public String formularioRegistro(Model model){
        ClienteDto cliente = new ClienteDto();
        model.addAttribute("cliente",cliente);
        return "formulario-registro";
    }

    //falta retornar correctamente la pagona html en caso de que el cliente se pueda registrar por el mometo retorna el inicio
    @PostMapping("/banco/registrar")
    public String registrarCliente(@ModelAttribute("cliente") ClienteDto cliente){
        if(servicioCliente.ingresarCliente(cliente) == 1){
            System.out.println("ya existe otro registro asociado con la misma identificacion");
            return "formulario-registro";
        }
        return "index";
    }

    @GetMapping("/cliente/solicitar_cita")
    public String formSolicitarCita(Model model){
        Cita cita = new Cita();
        model.addAttribute("cita",cita);
        return "formulario-cita";
    }

    @PostMapping("/cliente/preguardar")
    public String primerosDatosCita(@ModelAttribute("cita") Cita cita){
        servicioCliente.guardarCita(cita);
        String direccion = "/cliente/verificarDisponibilidad?idCita=" + cita.getId();
        return "redirect:"+ direccion;
    }



    @GetMapping("/cliente/verificarDisponibilidad")
    public String mostrarFormularioCita(@RequestParam("idCita") long id , @ModelAttribute("cita") Cita cita, Model model)  {
        List<LocalTime> opciones = servicioCliente.disponibilidadHoras(cita.getSede(),cita.getFecha(),cita.getServicio());
        System.out.println(servicioCliente.disponibilidadHoras(cita.getSede(),cita.getFecha(),cita.getServicio()));
        System.out.println(repoCita.listarDisponibilidad(cita.getSede(),cita.getFecha(),cita.getServicio()));
        Cita newCita = servicioCliente.obtenerCita(id);
        System.out.println(newCita.toString() + "este es la cita");
        //otros datos de cita definidos por el sitema
        model.addAttribute("cita",newCita);
        model.addAttribute("opciones",opciones);
        return "escogerHora";
    }

    @PostMapping("/cliente/guardar-cita")
    public String asignarCita(@ModelAttribute("cita") Cita cita){
        System.out.println("antes de guardar lo qye llega al metodo " + cita.toString());
        servicioCliente.guardarCita(cita);
        //System.out.println(cita.toString());
        return "index";
    }





}