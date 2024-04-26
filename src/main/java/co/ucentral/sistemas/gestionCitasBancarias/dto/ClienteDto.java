package co.ucentral.sistemas.gestionCitasBancarias.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {


    private long identificacion;

    private String nombre;

    private String correo;

    private String clave;


}
