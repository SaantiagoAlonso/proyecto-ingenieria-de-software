package co.ucentral.sistemas.gestionCitasBancarias.controladores;

import co.ucentral.sistemas.gestionCitasBancarias.dto.ClienteDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;
import co.ucentral.sistemas.gestionCitasBancarias.modeloCorreo.StructuraCorreo;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCita;
import co.ucentral.sistemas.gestionCitasBancarias.servicios.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
import java.util.List;

@Controller
public class ControladorCliente {

    @Autowired
    ServicioCliente servicioCliente;

    @Autowired
    RepoCita repoCita;

    private int i = 1;


    @GetMapping("/banco/inicio-sesion")
    public String formularioIngreso(Model model){
        ClienteDto cliente = new ClienteDto();
        model.addAttribute("cliente",cliente);
        return "formulario-ingreso";
    }

    @PostMapping("/banco/ingresar")
    public String iniciarSecion(@ModelAttribute("cliente") ClienteDto cliente){
        if(servicioCliente.inicioSesion(cliente)){
            return "inicio-cliente";
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

    @PostMapping("/banco/registrar")
    public String registrarCliente(@ModelAttribute("cliente") ClienteDto cliente){
        if(servicioCliente.ingresarCliente(cliente) == 1){
            return "formulario-registro";
        }
        return "inicio-cliente";
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
        Cita newCita = servicioCliente.obtenerCita(id);
        List<LocalTime> opciones = servicioCliente.disponibilidadHoras(newCita.getSede(),newCita.getFecha(),newCita.getServicio());
        model.addAttribute("cita",newCita);
        model.addAttribute("opciones",opciones);
        return "escogerHora";
    }

    @PostMapping("/cliente/guardar-cita")
    public String asignarCita(@ModelAttribute("cita") Cita cita){
        long sede = cita.getSede().getId_sede();
        String servicio = cita.getServicio().toLowerCase();

        cita.setEmpleado(servicioCliente.seleccionarEmpleado(sede,servicio));
        cita.setTurno(i);
        i = i + 1;

        servicioCliente.guardarCita(cita);

        StructuraCorreo structuraCorreo = new StructuraCorreo();
        structuraCorreo.setAsunto("¡Bienvenido al sistema del Banco Diner!");
        structuraCorreo.setMensaje("su cita ha sido asignada satesfactoriamente." +
               " hora: " + cita.getHora() + " sede: " + cita.getSede().getNombre() + " direccion: "
                       + cita.getSede().getDireccion() + " fecha: " +  cita.getFecha() + " servicio: " + cita.getServicio() );

        String correo = cita.getId_cliente().getCorreo();
        servicioCliente.enviarCorreo(correo,structuraCorreo);

        return "index";
    }





}