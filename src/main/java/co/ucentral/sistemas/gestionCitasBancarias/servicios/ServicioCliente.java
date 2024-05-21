package co.ucentral.sistemas.gestionCitasBancarias.servicios;

import co.ucentral.sistemas.gestionCitasBancarias.dto.ClienteDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cliente;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Sede;
import co.ucentral.sistemas.gestionCitasBancarias.operaciones.OperacionesCliente;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCita;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCliente;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoSede;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCliente implements OperacionesCliente {
    @Autowired
    RepoCliente repoCliente;

    @Autowired
    RepoCita repoCita;

    @Autowired
    RepoSede repoSede;

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
            System.out.println("se guardo cliente");
            return 0;
        }else{
            System.out.println(newCliente.getIdentificacion());
            return 1;
        }

    }

    @Override
    public List<LocalTime> disponibilidadHoras(Sede sede, LocalDate fecha, String servicio){
        //generar lista de horas posibles para asignar cita
        LocalTime inicio = LocalTime.of(8, 0);  // 8:00 AM
        LocalTime fin = LocalTime.of(16, 0);    // 4:00 PM

        int minutosIntervalo = 30;

        List<LocalTime> horas = new ArrayList<>();

        LocalTime horaActual = inicio;
        while (horaActual.isBefore(fin)) {
            horas.add(horaActual);
            horaActual = horaActual.plusMinutes(minutosIntervalo);
        }

        List<LocalTime> disponiblies = new ArrayList<>(horas);

        disponiblies.removeAll(repoCita.listarDisponibilidad(sede, fecha, servicio));


        return horas;
    }

    @Override
    public void guardarCita(Cita cita) {
        repoCita.save(cita);
    }

    public Cita obtenerCita(long id){
        Cita ncita = repoCita.getReferenceById(id);
        return ncita;
    }

}
