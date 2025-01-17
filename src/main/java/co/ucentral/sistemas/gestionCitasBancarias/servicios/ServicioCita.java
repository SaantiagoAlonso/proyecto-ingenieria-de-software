package co.ucentral.sistemas.gestionCitasBancarias.servicios;

import co.ucentral.sistemas.gestionCitasBancarias.dto.CitaDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;
import co.ucentral.sistemas.gestionCitasBancarias.operaciones.OperacionesCita;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCita;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import co.ucentral.sistemas.gestionCitasBancarias.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class ServicioCita implements OperacionesCita {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    RepoCita repoCita;


    @Override
    public CitaDto registrar(CitaDto citaDto) {
        Cita citas = repoCita.save(modelMapper.map(citaDto, Cita.class));
        return modelMapper.map(citas, CitaDto.class);
    }

    @Override
    public List<CitaDto> obtenerCitas() {
        TypeToken<List<CitaDto>> typeToken = new TypeToken<>() {
        };
        return modelMapper.map(repoCita.findAll(), typeToken.getType());
    }

    @Override
    public CitaDto obtenerCita(long serial) {
        Cita citas = repoCita.findById(serial).orElseThrow(
                ResourceNotFoundException::new);
        return modelMapper.map(citas, CitaDto.class);
    }

    @Override
    public Optional<Cita> obtenerCitaById(long serial) {
        return repoCita.findById(serial);
    }

    @Override
    public Cita actualizar(CitaDto citaDto) {
        Cita citaActualizada = repoCita.save(modelMapper.map(citaDto, Cita.class));
        return citaActualizada;
    }

    @Override
    public void eliminar(long serial) {
        repoCita.deleteById(serial);
    }

    @Override
    public void CerrarCita(Cita cita) {
        cita.setEstado("Terminado");
        repoCita.save(cita);
    }
    @Override
    public List<CitaDto> obtenerCitasPorEmpleado(long identificacion) {
        List<Cita> citas = repoCita.findByEmpleado_EmpId(identificacion);
        return modelMapper.map(citas, new TypeToken<List<CitaDto>>(){}.getType());
    }
}

