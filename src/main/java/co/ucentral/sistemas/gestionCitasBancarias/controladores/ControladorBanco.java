/*package co.ucentral.sistemas.gestionCitasBancarias.controladores;

import co.ucentral.sistemas.gestionCitasBancarias.dto.ClienteDto;
import co.ucentral.sistemas.gestionCitasBancarias.servicios.ServicioBanco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorBanco {

    @Autowired
    ServicioBanco servicioBanco;


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
        if(servicioBanco.ingresarCliente(cliente) == 1){
            System.out.println("ya existe un registro con esa identificacion");
            return "formulario-registro";
        }
        return "index";
    }


}
*/