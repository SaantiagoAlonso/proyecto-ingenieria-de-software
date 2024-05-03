package co.ucentral.sistemas.gestionCitasBancarias.servicios;

import co.ucentral.sistemas.gestionCitasBancarias.dto.ClienteDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cliente;
import co.ucentral.sistemas.gestionCitasBancarias.operaciones.Operaciones;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCita;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCliente;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.ucentral.sistemas.gestionCitasBancarias.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCliente implements Operaciones {
    @Autowired
    RepoCliente repoCliente;

    @Autowired
    RepoCita repoCita;

    @Autowired
    ModelMapper modelMapper;

    @Override
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

    @Override
    public Cita filtarCitas(LocalDate fecha){
        LocalTime inicio = LocalTime.of(8, 0);  // 8:00 AM
        LocalTime fin = LocalTime.of(16, 0);    // 4:00 PM

        int minutosIntervalo = 30;

        List<LocalTime> horas = new ArrayList<>();

        LocalTime horaActual = inicio;
        while (horaActual.isBefore(fin)) {
            horas.add(horaActual);
            horaActual = horaActual.plusMinutes(minutosIntervalo);
        }

        return new Cita();
    }





}
