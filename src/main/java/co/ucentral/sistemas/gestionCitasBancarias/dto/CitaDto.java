package co.ucentral.sistemas.gestionCitasBancarias.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDto {


    private long id;

    private int turno;

    private SedeDto sede;

    private LocalDate fecha;

    private LocalDateTime hora;

    private String servicio;

    private ClienteDto id_cliente;

    private EmpleadoDto id_empleado;

    private String estado;

}
