package co.ucentral.sistemas.gestionCitasBancarias.servicios;

import co.ucentral.sistemas.gestionCitasBancarias.dto.ClienteDto;
import co.ucentral.sistemas.gestionCitasBancarias.dto.EmpleadoDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cliente;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Empleado;
import co.ucentral.sistemas.gestionCitasBancarias.operaciones.OperacionesEmpleado;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCita;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCliente;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoEmpleado;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoSede;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
        System.out.println("EmpleadoDto ID: " + empleado.getEmpId()); // Verifica el ID en EmpleadoDto
        Empleado newEmpleado = modelMapper.map(empleado, Empleado.class);
        long identidad = newEmpleado.getEmpId();
        String clave = newEmpleado.getClave();
        boolean seguir = false;

        System.out.println("ID: " + identidad); // Verifica el ID después del mapeo
        System.out.println("Clave: " + clave);

        Optional<Empleado> empleadoOptional = repoEmpleado.findById(identidad);
        if (empleadoOptional.isPresent()) {
            Empleado empleadoEncontrado = empleadoOptional.get();
            System.out.println("Empleado encontrado: " + empleadoEncontrado.getEmpId());
            System.out.println("Clave en la base de datos: " + empleadoEncontrado.getClave());
            if (empleadoEncontrado.getClave().equals(clave)){
                System.out.println("Las claves coinciden");
                seguir = true;
            } else {
                System.out.println("Las claves no coinciden");
            }
        } else {
            System.out.println("No se encontró el empleado con la identificación proporcionada");
        }
        return seguir;
    }
}