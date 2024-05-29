package co.ucentral.sistemas.gestionCitasBancarias.repositorios;

import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalTime;
import java.util.List;

public interface RepoCita extends JpaRepository<Cita, Long>, JpaSpecificationExecutor<Cita> {


    @Query(value = "SELECT hora FROM citas WHERE fecha = :fecha AND servicio = :servicio AND id_sede = :idSede", nativeQuery = true)
    List<Time> listarDisponibilidad(LocalDate fecha, String servicio, Long idSede);


    //List<Cita> findByEmpleado_emp_id(long identificacionE);

    //List<Cita> findByEmpleado_Id(long emp_id);


    List<Cita> findByEmpleado_EmpId(long emp_id);


}