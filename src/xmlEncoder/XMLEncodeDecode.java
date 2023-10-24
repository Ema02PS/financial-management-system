package xmlEncoder;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class XMLEncodeDecode {
    public void encode(Object object, String fileName) {
        XMLEncoder encoder = null;

        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Al tratar de crear y abrir el archivo" + fileName);
        }
        encoder.writeObject(object);
        encoder.close();
    }

    public Object decode(String fileName) {
        XMLDecoder decoder = null;
        try {
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Al tratar de crear y abrir el archivo" + fileName);
        }
        Object object = decoder.readObject();
        return object;
    }
}