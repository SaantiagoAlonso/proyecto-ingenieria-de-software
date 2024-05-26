package co.ucentral.sistemas.gestionCitasBancarias.repositorios;

import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Empleado;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoSede extends JpaRepository<Sede, Long>, JpaSpecificationExecutor<Cita> {
}
