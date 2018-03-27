package menu;

import gestor.GestorOpciones;

import java.util.Scanner;

/**
 * Created by al361891 on 13/03/18.
 */
public class MenuSwitch {

    private GestorOpciones gestor = new GestorOpciones();

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for (MenuOpciones opcion : MenuOpciones.values()) {
            sb.append(opcion.ordinal());
            sb.append(".-");
            sb.append(opcion.getDescripcion());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void menuInicio() {
        System.out.println(getMenu());
        Scanner sc = new Scanner(System.in);
        System.out.println("Elije una opción:");
        byte opcion = sc.nextByte();
        MenuOpciones opcionMenu = MenuOpciones.getOpcion(opcion);

        switch (opcionMenu) {
            case SALIR:
                gestor.salir();
                break;
            case ALTA_CLIENTE:
                gestor.altaCliente();
                break;
            case BORRAR_CLIENTE:
                gestor.borrarCliente();
                break;
            case CAMBIAR_TARIFA:
                gestor.cambiarTarifa();
                break;
            case VER_CLIENTE:
                gestor.verCliente();
                break;
            case VER_TODOS_CLIENTES:
                gestor.verTodosClientes();
                break;
            case ALTA_LLAMADA:
                gestor.altaLlamada();
                break;
            case VER_LLAMADAS_CLIENTE:
                gestor.verLlamadasCliente();
                break;
            case EMITIR_FACTURA:
                gestor.emitirFactura();
                break;
            case VER_FACTURA:
                gestor.verFactura();
                break;
            case VER_FACTURAS_CLIENTE:
                gestor.verFacturasCliente();
                break;
            case VER_CLIENTES_FECHAS:
                gestor.verClientesFechas();
                break;
            case VER_LLAMADAS_CLIENTE_FECHAS:
                gestor.verLlamadasClienteFechas();
                break;
            case VER_FACTURAS_CLIENTE_FECHAS:
                gestor.verFacturasClienteFechas();
                break;
        }
    }
}
