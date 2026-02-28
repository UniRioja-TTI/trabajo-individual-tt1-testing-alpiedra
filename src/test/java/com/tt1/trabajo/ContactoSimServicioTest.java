package com.tt1.trabajo;

import org.junit.jupiter.api.*;
import servicios.ContactoSimServicio;
import modelo.DatosSolicitud;
import modelo.Entidad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ContactoSimServicioTest {

    private ContactoSimServicio servicio;

    @BeforeEach
    void setUp() {
        servicio = new ContactoSimServicio();
    }

    @AfterEach
    void tearDown() {
        servicio = null;
    }

    @Test
    void testGetEntitiesNoVacio() {
        List<Entidad> entidades = servicio.getEntities();
        assertNotNull(entidades);
        assertFalse(entidades.isEmpty());
    }

    @Test
    void testSolicitarSimulationDevuelveTokenPositivo() {
        Map<Integer, Integer> nums = new HashMap<>();
        nums.put(1, 10);
        DatosSolicitud ds = new DatosSolicitud(nums);

        int token = servicio.solicitarSimulation(ds);
        assertTrue(token > 0);
    }

    @Test
    void testIsValidEntityIdDevuelveTrue() {
        assertTrue(servicio.isValidEntityId());
    }
}