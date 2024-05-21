package co.ucentral.sistemas.gestionCitasBancarias.servicios;
import co.ucentral.sistemas.gestionCitasBancarias.dto.CitaDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;
import co.ucentral.sistemas.gestionCitasBancarias.operaciones.OperacionesCita;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCita;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import co.ucentral.sistemas.gestionCitasBancarias.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class ServicioCita implements OperacionesCita {

    final ModelMapper modelMapper;

    private final RepoCita repoCita;
    @Override
    public CitaDto registrar(CitaDto citaDto) {
        Cita citas = repoCita.save(modelMapper.map(citaDto, Cita.class));
        return modelMapper.map(citas, CitaDto.class);
    }
@Override
    public List<CitaDto> obtenerCitas() {
        TypeToken<List<CitaDto>> typeToken = new TypeToken<>() {};
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
    public CitaDto actualizar(CitaDto citaDto) {
        repoCita.save(modelMapper.map(citaDto, Cita.class));
        return citaDto;
    }
@Override
    public void eliminar(long serial) {
        repoCita.deleteById(serial);
    }

    @Override
    public void saveCita(CitaDto cita) {

    }

    @Override
    public List<CitaDto> getAllCitas() {
        return List.of();
    }

    @Override
    public CitaDto getCitaById(long id) {
        return null;
    }

    @Override
    public void updateCita(CitaDto cita) {

    }

    @Override
    public void deleteCita(long id) {

    }

    @Override
    public void CerrarCita(Cita cita){
        LocalTime horaInicio = cita.getHora();
        if (horaInicio.isBefore(cita.getHora().plusMinutes(5))){
            cita.setEstado("Terminado");
        }
    }
}
