package co.ucentral.sistemas.gestionCitasBancarias.entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ClienteDto")
@Table(name = "CLIENTES")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long identificacion;

    @Column
    private String nombre;

    @Column
    private String correo;

    @Column
    private String clave;


}
