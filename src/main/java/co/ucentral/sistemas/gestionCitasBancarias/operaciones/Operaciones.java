package co.ucentral.sistemas.gestionCitasBancarias.operaciones;

import co.ucentral.sistemas.gestionCitasBancarias.dto.ClienteDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;

import java.time.LocalDate;

public interface Operaciones {
    boolean inicioSesion(ClienteDto cliente);
    public int ingresarCliente(ClienteDto cliente);

    public Cita filtarCitas(LocalDate fecha);
}
