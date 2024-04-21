package co.ucentral.sistemas.gestionCitasBancarias.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Empleado")
@Table(name = "EMPLEADOS")
public class Empleado {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private String nombre;

    @Column
    private String carreo;

    @Column
    @ManyToOne
    @JoinColumn(name = "id_sede")
    private Sede sede;

    @Column
    private String cargo;

    @Column
    private String clave;


}
