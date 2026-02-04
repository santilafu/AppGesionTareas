package datos;

import jakarta.xml.bind.*;
import java.io.File;

public class ComponenteGestorXML {

    private static final String RUTA_DB = "bbdd_tareas.xml";

    // Método para GUARDAR (Escritura en BBDD XML)
    public void guardarCambios(BaseDatosXML bbdd) {
        try {
            JAXBContext context = JAXBContext.newInstance(BaseDatosXML.class);
            Marshaller marshaller = context.createMarshaller();

            // Formatear el XML para que se lea bien (saltos de línea)
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(bbdd, new File(RUTA_DB));
            System.out.println("Base de datos XML actualizada.");

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // Método para RECUPERAR (Lectura de BBDD XML)
    public BaseDatosXML cargarDatos() {
        File archivo = new File(RUTA_DB);
        if (!archivo.exists()) {
            return new BaseDatosXML(); // Si no existe, devolvemos una BBDD vacía
        }

        try {
            JAXBContext context = JAXBContext.newInstance(BaseDatosXML.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (BaseDatosXML) unmarshaller.unmarshal(archivo);

        } catch (JAXBException e) {
            e.printStackTrace();
            return new BaseDatosXML();
        }
    }
}