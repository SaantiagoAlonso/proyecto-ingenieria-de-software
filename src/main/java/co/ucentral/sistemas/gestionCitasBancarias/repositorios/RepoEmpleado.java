package co.ucentral.sistemas.gestionCitasBancarias.repositorios;

import co.ucentral.sistemas.gestionCitasBancarias.entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepoEmpleado extends JpaRepository<Empleado, Long>, JpaSpecificationExecutor<Empleado> {

    @Query(value = "SELECT * FROM empleados WHERE id_sede = :idSede AND cargo = :cargo", nativeQuery = true)
    Empleado findBySedeAndCargo(Long idSede, String cargo);


}
