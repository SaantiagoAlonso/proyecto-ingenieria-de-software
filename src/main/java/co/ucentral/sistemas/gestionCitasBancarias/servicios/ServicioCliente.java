package co.ucentral.sistemas.gestionCitasBancarias.servicios;

import co.ucentral.sistemas.gestionCitasBancarias.dto.ClienteDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cliente;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCliente;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import co.ucentral.sistemas.gestionCitasBancarias.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCliente implements Serializable {
    @Autowired
    RepoCliente repoCliente;

    @Autowired
    ModelMapper modelMapper;

    public boolean inicioSesion(ClienteDto cliente) {
        Cliente newCliente = modelMapper.map(cliente, Cliente.class);
        long identidad = newCliente.getIdentificacion();
        String clave = newCliente.getClave();
        Optional<Cliente> clienteOptional = repoCliente.findById(identidad);
        if (clienteOptional.isPresent()) {
            Cliente clienteEncontrado = clienteOptional.get();
            if (clienteEncontrado.getClave().equals(clave)) {
                return true;
            } else {

                return false;
            }
        } else {
            return false;
        }
    }

}
