package co.ucentral.sistemas.gestionCitasBancarias.repositorios;

import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Empleado;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Sede;
import co.ucentral.sistemas.gestionCitasBancarias.servicios.ServicioCita;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class RepoCitaTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    RepoCita repoCita;

    @DisplayName("guardar citas")
    @Test
    void guardarCitas() {

        Sede sede = new Sede();
        sede.setId_sede(1L); // Asigna un ID válido para la sede
        sede.setNombre("Norte");
        sede.setDireccion("Calle 170");


        Cita cita = new Cita();
        cita.setTurno(1);
        cita.setSede(sede);
        cita.setFecha(LocalDate.now());
        cita.setHora(LocalTime.now());
        cita.setServicio("Servicio de prueba");

        Cita citaGuardada = repoCita.save(cita);


        assertNotNull(citaGuardada);

    }

    @DisplayName("Listar citas")
    @Test
    void listarCitas() {
        List<Cita> citas = repoCita.findAll();

        assertNotNull(citas);

        assertEquals(0, citas.size());

    }

    @DisplayName("obtener cita")
    @Test
    void obtenerCitas() {
        Cita cita = repoCita.findById(1L).orElse(null);
        assertNotNull(cita);
    }

    @DisplayName("eliminar cita")
    @Test
    void eliminarCitas() {
        repoCita.deleteById(1L);

        assertEquals(0, repoCita.count());
    }
    @DisplayName("obtener citas por identificación de empleado")
    @Test
    void obtenerCitasPorIdentificacionEmpleado() {
        // Crear un empleado y asignarle una identificación
        Empleado empleado = new Empleado();
        empleado.setIdentificacion(123L);
        entityManager.persist(empleado);

        // Crear una cita y asignarle el empleado
        Cita cita = new Cita();
        cita.setEmpleado(empleado);
        entityManager.persist(cita);

        // Usar el método de prueba
        List<Cita> citas = repoCita.findByEmpleado_Identificacion(empleado.getIdentificacion());

        // Verificar que la lista de citas no está vacía
        assertFalse(citas.isEmpty());

        // Verificar que la cita recuperada tiene el empleado correcto
        assertEquals(empleado.getIdentificacion(), citas.get(0).getEmpleado().getIdentificacion());
    }
}