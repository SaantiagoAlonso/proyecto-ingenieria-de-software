package co.ucentral.sistemas.gestionCitasBancarias.entidades;

import co.ucentral.sistemas.gestionCitasBancarias.dto.EmpleadoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "EmpleadoDto")
@Table(name = "EMPLEADOS")
public class Empleado {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long identificacion;

    @Column
    private String nombre;

    @Column
    private String carreo;

    @ManyToOne
    @JoinColumn(name = "id_sede")
    private Sede sede;

    @Column
    private String cargo;

    @Column
    private String clave;


}
