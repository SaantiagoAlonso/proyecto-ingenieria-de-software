package co.ucentral.sistemas.gestionCitasBancarias.operaciones;

import co.ucentral.sistemas.gestionCitasBancarias.dto.CitaDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;

import java.util.List;
import java.util.Optional;

public interface OperacionesCita {
    CitaDto registrar(CitaDto citaDto);

    List<CitaDto> obtenerCitas();

    CitaDto obtenerCita(long serial);

    Optional<Cita> obtenerCitaById(long serial);

    CitaDto actualizar(CitaDto citaDto);

    void eliminar(long serial);

    void saveCita(CitaDto cita);

    List<CitaDto> getAllCitas();

    CitaDto getCitaById(long id);

    void updateCita(CitaDto cita);

    void deleteCita(long id);

    void CerrarCita(Cita cita);
}