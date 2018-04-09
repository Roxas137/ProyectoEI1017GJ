package menu;

import gestor.GestorOpciones;
import gestor.Metodos;

import java.util.Scanner;

/**
 * Created by al361891 on 13/03/18.
 */
public class MenuSwitch {

    private GestorOpciones gestor;

    public MenuSwitch(Metodos metodos){
        gestor = new GestorOpciones(metodos);
    }


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

    public void menuInicio(Scanner sc) {
        System.out.println(getMenu());
        sc = new Scanner(System.in);
        System.out.println("Elije una opción:");
        byte opcion = sc.nextByte();
        MenuOpciones opcionMenu = MenuOpciones.getOpcion(opcion);

        switch (opcionMenu) {
            case SALIR:
                gestor.salir();
                break;
            case ALTA_CLIENTE:
                gestor.altaCliente(sc);
                break;
            case BORRAR_CLIENTE:
                gestor.borrarCliente(sc);
                break;
            case CAMBIAR_TARIFA:
                gestor.cambiarTarifa(sc);
                break;
            case VER_CLIENTE:
                gestor.verCliente(sc);
                break;
            case VER_TODOS_CLIENTES:
                gestor.verTodosClientes(sc);
                break;
            case ALTA_LLAMADA:
                gestor.altaLlamada(sc);
                break;
            case VER_LLAMADAS_CLIENTE:
                gestor.verLlamadasCliente(sc);
                break;
            case EMITIR_FACTURA:
                gestor.emitirFactura(sc);
                break;
            case VER_FACTURA:
                gestor.verFactura(sc);
                break;
            case VER_FACTURAS_CLIENTE:
                gestor.verFacturasCliente(sc);
                break;
            case VER_CLIENTES_FECHAS:
                gestor.verClientesFechas(sc);
                break;
            case VER_LLAMADAS_CLIENTE_FECHAS:
                gestor.verLlamadasClienteFechas(sc);
                break;
            case VER_FACTURAS_CLIENTE_FECHAS:
                gestor.verFacturasClienteFechas(sc);
                break;
        }
    }
}
