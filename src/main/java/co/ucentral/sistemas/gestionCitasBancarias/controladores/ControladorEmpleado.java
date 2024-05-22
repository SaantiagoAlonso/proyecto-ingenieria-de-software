package co.ucentral.sistemas.gestionCitasBancarias.controladores;

import co.ucentral.sistemas.gestionCitasBancarias.dto.CitaDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCita;
import co.ucentral.sistemas.gestionCitasBancarias.servicios.ServicioCita;
import co.ucentral.sistemas.gestionCitasBancarias.servicios.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorEmpleado {

    @Autowired
    ServicioCita servicioCita;
    @Autowired
    ServicioCliente servicioCliente;
    @Autowired
    RepoCita repoCita;

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

    return "redirect:/empleado/cerrar-cita";}
}

