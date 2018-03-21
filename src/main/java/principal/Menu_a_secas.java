package principal;

import atributos.Llamada;
import atributos.Tarifa;
import clientes.Cliente;
import clientes.Particular;

import java.util.Date;
import java.util.Scanner;

import static principal.MenuOpciones.values;

/**
 * Created by al361891 on 13/03/18.
 */
public class Menu_a_secas {

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

    Metodos metodo = new Metodos();

    public void MenuInicio() {
        System.out.println(getMenu());
        Scanner sc = new Scanner(System.in);
        System.out.println("Elije una opción:");
        byte opcion = sc.nextByte();
        MenuOpciones opcionMenu = MenuOpciones.getOpcion(opcion);



        switch (opcionMenu) {
            case SALIR:
                System.exit(0);
                break;
            case ALTA_CLIENTE:
                Cliente nuevo = new Particular();
                metodo.addCliente(nuevo);
                break;
            case BORRAR_CLIENTE:
                String dni = "";
                metodo.removeCliente(dni);
                break;
            case CAMBIAR_TARIFA:
                Tarifa nueva = new Tarifa();
                break;
            case VER_CLIENTE:
                dni = "";
                //Metodo
                break;
            case VER_TODOS_CLIENTES:
                //metodo
                break;
            case ALTA_LLAMADA:
                Llamada llamada = new Llamada();
                //metodo
                break;
            case VER_LLAMADAS_CLIENTE:
                dni = "";
                //metodo
                break;
            case EMITIR_FACTURA:
                dni = "";
                //metodo
                break;
            case VER_FACTURA:
                int codigo = 0;
                System.out.println();
                break;
            case VER_FACTURAS_CLIENTE:
                dni = "";
                break;
            case VER_CLIENTES_FECHAS:
                Date fechaInicio;
                Date fechaFin;
                //metodo
                break;
            case VER_LLAMADAS_CLIENTE_FECHAS:

                break;
            case VER_FACTURAS_CLIENTE_FECHAS:
                //metodo
                break;
        }
    }
}
