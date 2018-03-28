package fichero;

import gestor.Metodos;

import java.io.*;

/**
 * Created by al361891 on 20/03/18.
 */
public class Salida extends Fichero implements Serializable{
    public void salida(Metodos metodo) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("datos.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(metodo);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
