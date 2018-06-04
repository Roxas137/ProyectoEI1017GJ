package modelo.fecha;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 * Created by al361891 on 13/03/18.
 */
public class FechaIntervalo<T extends Fecha> {
    private ArrayList<T> fechaCorrecta;

    public FechaIntervalo() {
        fechaCorrecta = new ArrayList<>();
    }

    public ArrayList<T> getFechaCorrecta() {
        return fechaCorrecta;
    }

    public void fechasIntervalo(Collection<T> datos, GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
        for (T dato : datos)
            if (dato.getFecha().after(fechaInicio) && dato.getFecha().before(fechaFin))
                fechaCorrecta.add(dato);
    }
}
