package co.ucentral.sistemas.gestionCitasBancarias.repositorios;

import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCliente extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {

}
