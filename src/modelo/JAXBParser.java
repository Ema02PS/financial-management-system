package modelo;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileOutputStream;

public class JAXBParser {

    //Se encarga de escribir
    public void marshall(Object o, String fileName) {
        try {
            //Creando el contexto JAXB
            JAXBContext jContext = JAXBContext.newInstance(o.getClass());
            //Se crea el objeto para realizar el Marshaller
            Marshaller marshallObj = jContext.createMarshaller();
            //Se configura la propiedad para mostrar el XML en el formato de salida
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallObj.marshal(o, new FileOutputStream(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Se encarga de leer
    public Object unmarshall(Object ref, String fileName) {
        //Se asigna null para evitar errores de configuración
        Object o = null;
        try {
            //getting the xml file to read
            File file = new File(fileName);
            //creating the JAXB context
            JAXBContext jContext = JAXBContext.newInstance(ref.getClass());
            //creating the unmarshall object
            Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
            //calling the unmarshall method
            o = unmarshallerObj.unmarshal(file);
            //Se hace un casteo para convertir a un objeto de tipo Student
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}
