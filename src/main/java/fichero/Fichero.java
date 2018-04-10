package fichero;

import gestor.Metodos;

import java.io.*;

/**
 * Created by al361891 on 27/03/18.
 */
public class Fichero {
    String nombreFichero;

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
        if (metodo == null)
            return new Metodos();
        return metodo;
    }

    public void salida(Metodos metodo) {
        FileOutputStream fos = null;

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
