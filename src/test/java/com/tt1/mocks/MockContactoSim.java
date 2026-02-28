package com.tt1.mocks;

import interfaces.InterfazContactoSim;
import modelo.DatosSimulation;
import modelo.DatosSolicitud;
import modelo.Entidad;

import java.util.ArrayList;
import java.util.List;

public class MockContactoSim implements InterfazContactoSim {

    private List<Entidad> entidades = new ArrayList<>();
    private int tokenADevolver = 12345;

    public MockContactoSim() {
        Entidad e = new Entidad();
        e.setId(1);
        e.setName("Entidad Test");
        e.setDescripcion("Para tests");
        entidades.add(e);
    }

    @Override
    public int solicitarSimulation(DatosSolicitud sol) {
        return tokenADevolver;
    }

    @Override
    public DatosSimulation descargarDatos(int ticket) {
        return new DatosSimulation();
    }

    @Override
    public List<Entidad> getEntities() {
        return entidades;
    }

    @Override
    public boolean isValidEntityId() {
        return true;
    }
}