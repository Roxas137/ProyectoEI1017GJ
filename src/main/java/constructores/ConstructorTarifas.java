package constructores;

import tarifa.Basica;
import tarifa.DiaReducido;
import tarifa.HoraReducida;
import tarifa.Tarifa;

public class ConstructorTarifas {
    public Tarifa getInstanceBasica() {
        return new Basica();
    }
    public Tarifa getInstanceDiaReducido() {
        return new DiaReducido();
    }
    public Tarifa getInstanceHoraReducida() {
        return new HoraReducida();
    }
}
