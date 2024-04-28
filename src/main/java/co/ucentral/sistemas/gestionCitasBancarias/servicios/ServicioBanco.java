package co.ucentral.sistemas.gestionCitasBancarias.servicios;

import co.ucentral.sistemas.gestionCitasBancarias.dto.ClienteDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cliente;
import co.ucentral.sistemas.gestionCitasBancarias.operaciones.Operaciones;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioBanco implements Operaciones {

    @Autowired
    RepoCliente repoCliente;

    @Autowired
    ModelMapper modelMapper;

    //este metodo permite registrar el cliente comprueba si ya existe un registro con la misma identificacion si no exite registra el cliente
    @Override
    public int ingresarCliente(ClienteDto cliente) {
        Cliente newCliente = modelMapper.map(cliente,Cliente.class);
        long identidad = newCliente.getIdentificacion();
        if(!repoCliente.findById(identidad).isPresent()){
            repoCliente.save(newCliente);
            return 0;
        }else{
            System.out.println(newCliente.getIdentificacion());
            return 1;
        }


    }
}
