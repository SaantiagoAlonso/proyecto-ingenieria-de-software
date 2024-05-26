package co.ucentral.sistemas.gestionCitasBancarias.entidades;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cita")
@Table(name = "CITAS")
@ToString
@Builder
public class Cita implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int turno;

    @ManyToOne
    @JoinColumn(name = "id_sede")
    private Sede sede;

    @Column
    private LocalDate fecha;

    @Column
    private LocalTime hora;

    @Column
    private String servicio;

    @ManyToOne
    @JoinColumn(name = "empleado_identificacion")
    private Empleado empleado;

    @Column
    private String estado;

    @Column
    private String comentarios;

}
