package constructores;

import tarifa.Basica;
import tarifa.DiaReducido;
import tarifa.HoraReducida;
import tarifa.Tarifa;

public class ConstructorTarifa {
    public Tarifa getInstanceBasica() {
        return new Basica();
    }
    public Tarifa getInstanceDiaReducido(Tarifa tarifa, int dia, double precio) {
        return new DiaReducido(tarifa, dia, precio);
    }
    public Tarifa getInstanceHoraReducida(Tarifa tarifa, int horaInicio, int horaFin, double precio) {
        return new HoraReducida(tarifa, horaInicio, horaFin, precio);
    }
}
