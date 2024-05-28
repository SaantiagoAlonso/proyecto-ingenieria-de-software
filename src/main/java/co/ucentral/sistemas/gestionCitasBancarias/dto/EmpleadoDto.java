package co.ucentral.sistemas.gestionCitasBancarias.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDto {

    private long identificacion;

    private String nombre;

    private String carreo;

    private SedeDto sede;

    private String cargo;

    private String clave;


}
