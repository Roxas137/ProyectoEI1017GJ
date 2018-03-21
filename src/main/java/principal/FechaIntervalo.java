package principal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by al361891 on 13/03/18.
 */
public class FechaIntervalo<T extends Fecha> {
    private ArrayList<T> fechaCorrecta;

    public FechaIntervalo(){
        fechaCorrecta = new ArrayList<>();
    }

    public ArrayList<T> getFechaCorrecta() {
        return fechaCorrecta;
    }

    public void fechasIntervalo(Collection<T> datos, Date fechaInicio, Date fechaFin){
        for(T dato : datos)
            if (dato.getFecha().compareTo(fechaInicio) > 0 && dato.getFecha().compareTo(fechaFin) < 0)
                fechaCorrecta.add(dato);
    }

}
