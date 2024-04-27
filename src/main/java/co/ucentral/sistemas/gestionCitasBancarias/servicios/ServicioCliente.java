package co.ucentral.sistemas.gestionCitasBancarias.servicios;

import co.ucentral.sistemas.gestionCitasBancarias.dto.ClienteDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cliente;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCliente;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.io.Serializable;
import java.util.List;
import co.ucentral.sistemas.gestionCitasBancarias.exception.ResourceNotFoundException;
public class ServicioCliente implements Serializable {
    private ModelMapper modelMapper;
    private RepoCliente repoClien;

    public ClienteDto registrar(ClienteDto clienteDTO) {
        Cliente clien = repoClien.save(modelMapper.map(clienteDTO, Cliente.class));
        return modelMapper.map(clien, ClienteDto.class);
    }

    public List<ClienteDto> obtenerClientes() {
        TypeToken<List<ClienteDto>> typeToken = new TypeToken<>() {
        };
        return modelMapper.map(repoClien.findAll(), typeToken.getType());
    }

    public ClienteDto obtenerCliente(long serial) {
        Cliente cliente = repoClien.findById(serial).orElseThrow(
                ResourceNotFoundException::new);
        return modelMapper.map(cliente, ClienteDto.class);
    }

    public ClienteDto actualizar(ClienteDto equipoDto) {
        repoClien.save(modelMapper.map(equipoDto, Cliente.class));
        return equipoDto;
    }

}
