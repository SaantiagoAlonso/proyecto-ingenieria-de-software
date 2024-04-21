package co.ucentral.sistemas.gestionCitasBancarias.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Sede")
@Table(name = "SEDES")
public class Sede {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SEDE")
    private long id_sede;

    @Column(name ="SED_NOMBRE" )
    private String nombre;

    @Column
    private String direccion;



}
