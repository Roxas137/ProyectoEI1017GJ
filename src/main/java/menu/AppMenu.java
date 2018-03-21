package menu;

import java.util.Scanner;

/**
 * Created by al361891 on 27/02/18.
 */

public enum AppMenu{
    SALIR("Salir de la aplicacion"),
    ALTA_CLIENTE("Dar de alta un nuevo cliente"),
    BORRAR_CLIENTE("Borrar un cliente"),
    CAMBIAR_TARIFA("Cambiar la tarifa de un cliente"),
    VER_CLIENTE("Ver los datos de un cliente"),
    VER_TODOS_CLIENTES("Ver el total de los clientes"),
    ALTA_LLAMADA("Dar de alta una llamada"),
    VER_LLAMADAS_CLIENTE("Ver todas las llamadas de un cliente"),
    EMITIR_FACTURA("Emitir una factura para un cliente"),
    VER_FACTURA("Ver los datos de una factura"),
    VER_FACTURAS_CLIENTE("Ver todas las facturas de un cliente");

    private String descripcion;

    private AppMenu(String descripcion) {
        this.descripcion = descripcion;
    }
}