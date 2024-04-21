package co.ucentral.sistemas.gestionCitasBancarias.entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cita")
@Table(name = "CITAS")
public class Cita {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private int turno;

    @Column
    @ManyToOne
    @JoinColumn(name = "id_sede")
    private Sede sede;

    @Column
    private LocalDate fecha;

    @Column
    private LocalDateTime hora;

    @Column
    private String servicio;

    @Column
    @ManyToOne
    @JoinColumn(name = "identificacion")
    private Cliente id_cliente;

    @Column
    @ManyToOne
    @JoinColumn(name = "id")
    private Empleado id_empleado;

    @Column
    private String estado;


}
