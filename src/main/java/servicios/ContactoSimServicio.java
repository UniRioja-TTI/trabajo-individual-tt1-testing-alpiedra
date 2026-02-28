package servicios;
import interfaces.InterfazContactoSim;
import modelo.DatosSimulation;
import modelo.DatosSolicitud;
import modelo.Entidad;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ContactoSimServicio implements InterfazContactoSim {
    private final List<Entidad> entidades;
    private DatosSolicitud ultimaSolicitud;
    public ContactoSimServicio() {
        entidades = new ArrayList<>();

        Entidad e1 = new Entidad();
        e1.setId(1);
        e1.setName("Nombre uno");
        e1.setDescripcion("Nombre ejemplo");

        Entidad e2 = new Entidad();
        e2.setId(2);
        e2.setName("Nombre dos");
        e2.setDescripcion("Nombre ejemplo");
        entidades.add(e1);
        entidades.add(e2);
    }
    @Override
    public int solicitarSimulation(DatosSolicitud sol) {
        this.ultimaSolicitud = sol;
        return new Random().nextInt(90000) + 10000;
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