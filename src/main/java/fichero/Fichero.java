package fichero;

import gestor.Metodos;

import java.io.*;

/**
 * Created by al361891 on 27/03/18.
 */
public class Fichero {

    public Metodos entrada() {
        Metodos metodo = null;
        try {
            FileInputStream fis = new FileInputStream("datos.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            metodo = (Metodos) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metodo != null ? metodo : new Metodos();
    }

    public void salida(Metodos metodo) {
        FileOutputStream fos;
        try {
            File fichero = new File("datos.bin");
            fos = new FileOutputStream(fichero);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(metodo);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
