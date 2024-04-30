package co.ucentral.sistemas.gestionCitasBancarias.controladores;

import co.ucentral.sistemas.gestionCitasBancarias.servicios.ServicioCliente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class ControladorCliente {
    private static final Logger logger = LogManager.getLogger(ControladorCliente.class);
    @Autowired
    ServicioCliente servicioCliente;

}