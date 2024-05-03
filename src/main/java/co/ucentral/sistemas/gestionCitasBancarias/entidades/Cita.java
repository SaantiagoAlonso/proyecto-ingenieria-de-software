package co.ucentral.sistemas.gestionCitasBancarias.entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CitaDto")
@Table(name = "CITAS")
@ToString
public class Cita {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private int turno;

    @ManyToOne
    @JoinColumn(name = "id_sede")
    private Sede sede;

    @Column
    private LocalDate fecha;

    @Column
    private LocalDateTime hora;

    @Column
    private String servicio;

    @ManyToOne
    @JoinColumn(name = "identificacion")
    private Cliente id_cliente;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Empleado id_empleado;

    @Column
    private String estado;

}
