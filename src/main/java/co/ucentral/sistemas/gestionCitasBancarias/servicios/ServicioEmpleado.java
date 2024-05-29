package co.ucentral.sistemas.gestionCitasBancarias.servicios;

import co.ucentral.sistemas.gestionCitasBancarias.dto.EmpleadoDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Empleado;
import co.ucentral.sistemas.gestionCitasBancarias.operaciones.OperacionesEmpleado;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCita;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCliente;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoEmpleado;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoSede;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ServicioEmpleado implements OperacionesEmpleado {
    @Autowired
    RepoCliente repoCliente;

    @Autowired
    RepoCita repoCita;

    @Autowired
    RepoSede repoSede;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RepoEmpleado repoEmpleado;


    public boolean inicioSesion(EmpleadoDto empleado) {
        Empleado newEmpleado = modelMapper.map(empleado, Empleado.class);
        long identidad = newEmpleado.getEmpId();
        String clave = newEmpleado.getClave();
        boolean seguir = false;
        Optional<Empleado> empleadoOptional = repoEmpleado.findById(identidad);
        if (empleadoOptional.isPresent()) {
            Empleado empleadoEncontrado = empleadoOptional.get();
            if (empleadoEncontrado.getClave().equals(clave)){
                seguir = true;
            }
        }
        return seguir;
    }
}