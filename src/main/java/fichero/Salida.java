package fichero;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by al361891 on 20/03/18.
 */
public class Salida extends Fichero implements Serializable{
    public void Salida() {
        FileOutputStream fos = new FileOutputStream("datos.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(gestor.Metodos metodos);
        oos.close();
    }
}
