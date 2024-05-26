package co.ucentral.sistemas.gestionCitasBancarias.repositorios;

import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalTime;
import java.util.List;
@Repository
public interface RepoCita extends JpaRepository<Cita, Long>, JpaSpecificationExecutor<Cita> {

    @Query("SELECT c.hora FROM Cita c " +
            "WHERE c.sede = :sede " +
            "AND c.fecha = :fecha " +
            "AND c.servicio = :servicio ")
    public List<LocalTime> listarDisponibilidad(Sede sede, LocalDate fecha, String servicio);
    List<Cita> findByEmpleadoIdentificacion(long identificacion);
}