package co.ucentral.sistemas.gestionCitasBancarias.controladores;

import co.ucentral.sistemas.gestionCitasBancarias.dto.CitaDto;
import co.ucentral.sistemas.gestionCitasBancarias.dto.EmpleadoDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCita;
import co.ucentral.sistemas.gestionCitasBancarias.servicios.ServicioCita;
import co.ucentral.sistemas.gestionCitasBancarias.servicios.ServicioCliente;
import co.ucentral.sistemas.gestionCitasBancarias.servicios.ServicioEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ControladorEmpleado {

    @Autowired
    ServicioCita servicioCita;
    @Autowired
    ServicioCliente servicioCliente;
    @Autowired
    ServicioEmpleado servicioEmpleado;
    @Autowired
    RepoCita repoCita;

    @GetMapping("/empleado/inicio-sesion")
    public String formularioIngreso(Model model) {
        EmpleadoDto empleado = new EmpleadoDto();
        model.addAttribute("empleado", empleado);
        return "formulario-ingreso-empleado";
    }

    @PostMapping("/empleado/ingresar")
    public String iniciarSesion(@ModelAttribute("empleado") EmpleadoDto empleado) {
        // Depuración: Imprimir datos recibidos del formulario
        System.out.println("Datos recibidos del formulario - ID: " + empleado.getIdentificacion() + ", Clave: " + empleado.getClave());

        boolean loginExitoso = servicioEmpleado.inicioSesion(empleado);
        System.out.println("Login exitoso: " + loginExitoso);

        if (loginExitoso) {
            return "redirect:/empleado/portal-empleado";
        }
        System.out.println("Login fallido, redirigiendo a index.");
        return "index";
    }

    @PostMapping("/empleado/cerrar-cita")
    public String cerrarCita (@ModelAttribute("cita") CitaDto citaDto){
        Cita cita = repoCita.getReferenceById(citaDto.getId());
        servicioCita.CerrarCita(cita);
        return "index";
    }

    @GetMapping("/empleado/manejo-cita")
    public String manejoCita(@ModelAttribute("cita") CitaDto citaDto, Model model) {
        Cita cita = repoCita.getReferenceById(citaDto.getId());
        model.addAttribute("cita", cita);
        if (!(cita.getHora() == cita.getHora().plusMinutes(5) && cita.getEstado().equals("En Curso"))){
            return "redirect:/empleado/cerrar-cita";}
        return null;
    }

    @GetMapping("/empleado/transcurso-cita")
    public String transcursoCita(@ModelAttribute("cita") CitaDto citaDto, Model model) {
        Cita cita = repoCita.getReferenceById(citaDto.getId());
        model.addAttribute("cita", cita);
    return "redirect:/empleado/cerrar-cita";
    }

    @GetMapping("/empleado/portal-empleado")
    public String portalEmpleado(@ModelAttribute("cita") EmpleadoDto empleadoDto) {
    return "portal-empleado";
    }

    @GetMapping("/empleado/{empleadoId}")
    public List<Cita> listarCitasPorEmId(@PathVariable long empleadoId) {
        return servicioCita.findCitasByEmp_Id(empleadoId);
    }
    @GetMapping("/citas/{empleadoId}")
    public List<Cita> obtenerCitasPorEmpleado(@PathVariable Long empleadoId) {
        return servicioCita.findCitasByEmp_Id(empleadoId);
    }
}

