package controlador.constructores;

import controlador.tarifa.Basica;
import controlador.tarifa.DiaReducido;
import controlador.tarifa.HoraReducida;
import controlador.tarifa.Tarifa;

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
