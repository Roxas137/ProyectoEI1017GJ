package constructores;

import clientes.Cliente;
import clientes.Empresa;
import clientes.Particular;

public class ConstructorCliente {
    public Cliente getInstanceEmpresa() {
        return new Empresa();
    }
    public Cliente getInstanceParticular() {
        return new Particular("");
    }
    public  Cliente getInstanceParticular(String apellido) {
        return new Particular(apellido);
    }
}
