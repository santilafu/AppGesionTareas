import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "gestion_empresarial") // Raíz de la Base de Datos
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseDatosXML {

    @XmlElement(name = "tarea")
    private List<Tarea> listaTareas = new ArrayList<>();

    public List<Tarea> getListaTareas() {
        return listaTareas;
    }

    public void setListaTareas(List<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }

    public void agregarTarea(Tarea t) {
        this.listaTareas.add(t);
    }
}