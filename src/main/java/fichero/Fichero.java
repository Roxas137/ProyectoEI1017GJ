package fichero;

import gestor.Metodos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by al361891 on 27/03/18.
 */
public class Fichero {
    String nombreFichero;

    public void entrada(Metodos metodo){

    }

    public void salida(Metodos metodo) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("datos.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(metodo);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
