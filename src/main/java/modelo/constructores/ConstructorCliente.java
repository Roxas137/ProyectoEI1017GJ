package modelo.constructores;

import modelo.clientes.Cliente;
import modelo.clientes.Empresa;
import modelo.clientes.Particular;

public class ConstructorCliente {
    public Cliente getInstanceEmpresa() {
        return new Empresa();
    }

    public Cliente getInstanceParticular(String apellido) {
        return new Particular(apellido);
    }
}
