package controlador.constructores;

import controlador.clientes.Cliente;
import controlador.clientes.Empresa;
import controlador.clientes.Particular;

public class ConstructorCliente {
    public Cliente getInstanceEmpresa() {
        return new Empresa();
    }

    public Cliente getInstanceParticular(String apellido) {
        return new Particular(apellido);
    }
}
