package co.ucentral.sistemas.gestionCitasBancarias.repositorios;

import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface RepoCliente extends CrudRepository<Cliente,Long>, JpaSpecificationExecutor<Cliente> {
}
