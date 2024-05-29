package co.ucentral.sistemas.gestionCitasBancarias.operaciones;

import co.ucentral.sistemas.gestionCitasBancarias.dto.ClienteDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Sede;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface Operaciones {
    boolean inicioSesion(ClienteDto cliente);

    public int ingresarCliente(ClienteDto cliente);

    public List<LocalTime> disponibilidadHoras(Sede sede, LocalDate fecha, String servicio);

    void guardarCita(Cita cita);
}
