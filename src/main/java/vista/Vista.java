package vista;

import controlador.Controlador;
import controlador.gestor.GestorOpciones;
import modelo.Modelo;
import modelo.clientes.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Vista implements InterfazVista {
    private Controlador controlador;
    private Modelo modelo;
    //Añadir botones y todo eso

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }


    private void ventanaPrincipal(GestorOpciones gestorOpciones) {
        JFrame ventana = new JFrame("Gestión de llamadas");
        //JPanel opciones = new JPanel();
        Container contenedor = ventana.getContentPane();

        Escuchador escuchador = new Escuchador();
        //Creamos los botones y asignamos el escuchador
        JButton altaCliente = new JButton("Dar de alta un cliente");
        altaCliente.addActionListener(escuchador);
        JButton borrarCliente = new JButton("Borrar un cliente");
        borrarCliente.addActionListener(escuchador);
        JButton cambiarTarifa = new JButton("Cambiar la tarifa");
        cambiarTarifa.addActionListener(escuchador);
        JButton verCliente = new JButton("Ver un cliente");
        verCliente.addActionListener(escuchador);
        JButton verTodosClientes = new JButton("Ver todos los clientes");
        verTodosClientes.addActionListener(escuchador);
        JButton altaLlamada = new JButton("Dar de alta una llamada");
        altaLlamada.addActionListener(escuchador);
        JButton verLlamadasCliente = new JButton("Ver las llamadas de un cliente");
        verLlamadasCliente.addActionListener(escuchador);
        JButton emitirFactura = new JButton("Emitir una factura");
        emitirFactura.addActionListener(escuchador);
        JButton verFactura = new JButton("Ver una factura");
        verFactura.addActionListener(escuchador);
        JButton verFacturasCliente = new JButton("Ver las facturas de un cliente");
        verFacturasCliente.addActionListener(escuchador);
        JButton verClientesFechas = new JButton("Ver los clientes dados de alta entre dos fechas");
        verClientesFechas.addActionListener(escuchador);
        JButton verLlamadasClienteFechas = new JButton("Ver llamadas entre dos fechas");
        verLlamadasClienteFechas.addActionListener(escuchador);
        JButton verFacturasClienteFechas = new JButton("Ver las facturas de un cliente entre dos fechas");
        verFacturasClienteFechas.addActionListener(escuchador);

        //Añadimos las opciones al panel
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        altaCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(altaCliente);
        borrarCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(borrarCliente);
        cambiarTarifa.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(cambiarTarifa);
        verCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(verCliente);
        verTodosClientes.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(verTodosClientes);
        altaLlamada.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(altaLlamada);
        verLlamadasCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(verLlamadasCliente);
        emitirFactura.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(emitirFactura);
        verFactura.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(verFactura);
        verFacturasCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(verFacturasCliente);
        verClientesFechas.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(verClientesFechas);
        verLlamadasClienteFechas.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(verLlamadasClienteFechas);
        verFacturasClienteFechas.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(verFacturasClienteFechas);

        ventana.addWindowListener(new VentanaPrincipal(gestorOpciones));
        ventana.pack();
        ventana.setVisible(true);
    }

    private void ventanaAltaCliente(){
        JFrame ventana = new JFrame("Dar de alta un cliente");
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));

        JRadioButton empresa = new JRadioButton("Empresa");
        empresa.setActionCommand("empresa");
        JRadioButton particular = new JRadioButton("Particular");
        particular.setActionCommand("particular");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(empresa);
        grupo.add(particular);
        empresa.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(empresa);
        particular.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(particular);

        JTextField nombre = new JTextField(20);
        JLabel nombreLabel = new JLabel("Nombre:");
        contenedor.add(nombreLabel);
        contenedor.add(nombre);

        JTextField apellidos = new JTextField(20);
        JLabel apellidosLabel = new JLabel("Apellidos:");
        contenedor.add(apellidosLabel);
        contenedor.add(apellidos);

        JTextField dni = new JTextField(20);
        JLabel dniLabel = new JLabel("DNI/CIF:");
        contenedor.add(dniLabel);
        contenedor.add(dni);

        JTextField codpostal = new JTextField(20);
        JLabel codpostalLabel = new JLabel("Codigo Postal:");
        contenedor.add(codpostalLabel);
        contenedor.add(codpostal);

        JTextField provincia = new JTextField(20);
        JLabel provinciaLabel = new JLabel("Provincia:");
        contenedor.add(provinciaLabel);
        contenedor.add(provincia);

        JTextField poblacion = new JTextField(20);
        JLabel poblacionLabel = new JLabel("Poblacion:");
        contenedor.add(poblacionLabel);
        contenedor.add(poblacion);

        JTextField email = new JTextField(20);
        JLabel emailLabel = new JLabel("E-mail:");
        contenedor.add(emailLabel);
        contenedor.add(email);

        JButton anyadir = new JButton("Añadir");
        anyadir.setAlignmentX(Component.LEFT_ALIGNMENT);
        contenedor.add(anyadir);

        ventana.pack();
        ventana.setVisible(true);
    }

    private void ventanaBorrarCliente(){
        JFrame ventana = new JFrame("Borrar un cliente");
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JTextField dni = new JTextField(9);
        JLabel dniLabel = new JLabel("DNI:");
        contenedor.add(dniLabel);
        contenedor.add(dni);
        JButton borrar = new JButton("Borrar");
        borrar.setAlignmentX(Component.LEFT_ALIGNMENT);
        contenedor.add(borrar);
        ventana.pack();
        ventana.setVisible(true);
    }

    private void ventanaCambiarTarifa(){
        JFrame ventana = new JFrame("Cambiar Tarifa");
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JTextField dni = new JTextField(9);
        JLabel dniLabel = new JLabel("DNI:");
        contenedor.add(dniLabel);
        contenedor.add(dni);


        ventana.pack();
        ventana.setVisible(true);
    }
    //todo mostrar el cliente buscado
    private void ventanaVerCliente(){
        JFrame ventana = new JFrame("Ver cliente");
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JTextField dni = new JTextField(9);
        JLabel dniLabel = new JLabel("DNI:");
        contenedor.add(dniLabel);
        contenedor.add(dni);


        ventana.pack();
        ventana.setVisible(true);
    }

    private void ventanaVerTodosClientes(){
        //ventana con una lista de los datos de todos los clientes
        JFrame ventana = new JFrame("Ver Todos los Clientes");
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));



        ventana.pack();
        ventana.setVisible(true);
    }

    private void ventanaAltaLlamada(){
        JFrame ventana = new JFrame("Alta llamada");
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JTextField dni = new JTextField(9);
        JLabel dniLabel = new JLabel("DNI:");
        contenedor.add(dniLabel);
        contenedor.add(dni);

        JTextField telefono = new JTextField(9);
        JLabel telefonoLabel = new JLabel("Telefono:");
        contenedor.add(telefonoLabel);
        contenedor.add(telefono);

        JTextField duracion = new JTextField(9);
        JLabel duracionLabel = new JLabel("Duracion:");
        contenedor.add(duracionLabel);
        contenedor.add(duracion);

        ventana.pack();
        ventana.setVisible(true);
    }

    private void ventanaVerLlamadas(){
        JFrame ventana = new JFrame("Ver llamadas");
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JTextField dni = new JTextField(9);
        JLabel dniLabel = new JLabel("DNI:");
        contenedor.add(dniLabel);
        contenedor.add(dni);


        ventana.pack();
        ventana.setVisible(true);
    }

    private void ventanaEmitirFactura(){
        JFrame ventana = new JFrame("Emitir factura");
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JTextField dni = new JTextField(9);
        JLabel dniLabel = new JLabel("DNI:");
        contenedor.add(dniLabel);
        contenedor.add(dni);


        ventana.pack();
        ventana.setVisible(true);
    }

    private void ventanaVerFactura(){
        JFrame ventana = new JFrame("Ver Factura");
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JTextField factura = new JTextField(9);
        JLabel facturaLabel = new JLabel("Código de la factura:");
        contenedor.add(facturaLabel);
        contenedor.add(factura);


        ventana.pack();
        ventana.setVisible(true);
    }

    private void ventanaVerFacturasCliente(){
        JFrame ventana = new JFrame("Ver Facturas de un cliente");
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JTextField dni = new JTextField(9);
        JLabel dniLabel = new JLabel("DNI:");
        contenedor.add(dniLabel);
        contenedor.add(dni);


        ventana.pack();
        ventana.setVisible(true);
    }

    private void ventanaVerClientesFechas(){
        JFrame ventana = new JFrame("Ver Clientes entre fechas");
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JTextField diaInicio = new JTextField(9);
        JLabel diaInicioLabel = new JLabel("Dia Inicio:");
        contenedor.add(diaInicioLabel);
        contenedor.add(diaInicio);
        JTextField mesInicio = new JTextField(9);
        JLabel mesInicioLabel = new JLabel("Mes Inicio:");
        contenedor.add(mesInicioLabel);
        contenedor.add(mesInicio);
        JTextField anyoInicio = new JTextField(9);
        JLabel anyoInicioLabel = new JLabel("Año Inicio:");
        contenedor.add(anyoInicioLabel);
        contenedor.add(anyoInicio);

        JTextField diaFin = new JTextField(9);
        JLabel diaFinLabel = new JLabel("Dia Fin:");
        contenedor.add(diaFinLabel);
        contenedor.add(diaFin);
        JTextField mesFin = new JTextField(9);
        JLabel mesFinLabel = new JLabel("Mes Fin:");
        contenedor.add(mesFinLabel);
        contenedor.add(mesFin);
        JTextField anyoFin = new JTextField(9);
        JLabel anyoFinLabel = new JLabel("Año Fin:");
        contenedor.add(anyoFinLabel);
        contenedor.add(anyoFin);


        ventana.pack();
        ventana.setVisible(true);
    }

    private void ventanaVerLlamadasFechas(){
        JFrame ventana = new JFrame("Ver llamadas entre fechas");
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));

        JTextField diaInicio = new JTextField(9);
        JLabel diaInicioLabel = new JLabel("Dia Inicio:");
        contenedor.add(diaInicioLabel);
        contenedor.add(diaInicio);
        JTextField mesInicio = new JTextField(9);
        JLabel mesInicioLabel = new JLabel("Mes Inicio:");
        contenedor.add(mesInicioLabel);
        contenedor.add(mesInicio);
        JTextField anyoInicio = new JTextField(9);
        JLabel anyoInicioLabel = new JLabel("Año Inicio:");
        contenedor.add(anyoInicioLabel);
        contenedor.add(anyoInicio);

        JTextField diaFin = new JTextField(9);
        JLabel diaFinLabel = new JLabel("Dia Fin:");
        contenedor.add(diaFinLabel);
        contenedor.add(diaFin);
        JTextField mesFin = new JTextField(9);
        JLabel mesFinLabel = new JLabel("Mes Fin:");
        contenedor.add(mesFinLabel);
        contenedor.add(mesFin);
        JTextField anyoFin = new JTextField(9);
        JLabel anyoFinLabel = new JLabel("Año Fin:");
        contenedor.add(anyoFinLabel);
        contenedor.add(anyoFin);

        ventana.pack();
        ventana.setVisible(true);
    }

    private void ventanaVerFacturasFechas(){
        JFrame ventana = new JFrame("Ver Facturas entre fechas");
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));

        JTextField diaInicio = new JTextField(9);
        JLabel diaInicioLabel = new JLabel("Dia Inicio:");
        contenedor.add(diaInicioLabel);
        contenedor.add(diaInicio);
        JTextField mesInicio = new JTextField(9);
        JLabel mesInicioLabel = new JLabel("Mes Inicio:");
        contenedor.add(mesInicioLabel);
        contenedor.add(mesInicio);
        JTextField anyoInicio = new JTextField(9);
        JLabel anyoInicioLabel = new JLabel("Año Inicio:");
        contenedor.add(anyoInicioLabel);
        contenedor.add(anyoInicio);

        JTextField diaFin = new JTextField(9);
        JLabel diaFinLabel = new JLabel("Dia Fin:");
        contenedor.add(diaFinLabel);
        contenedor.add(diaFin);
        JTextField mesFin = new JTextField(9);
        JLabel mesFinLabel = new JLabel("Mes Fin:");
        contenedor.add(mesFinLabel);
        contenedor.add(mesFin);
        JTextField anyoFin = new JTextField(9);
        JLabel anyoFinLabel = new JLabel("Año Fin:");
        contenedor.add(anyoFinLabel);
        contenedor.add(anyoFin);

        ventana.pack();
        ventana.setVisible(true);
    }


    public void creaGUI(GestorOpciones gestor) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ventanaPrincipal(gestor);
            }
        });
    }

    class VentanaPrincipal extends WindowAdapter {
        GestorOpciones gestorOpciones;

        private VentanaPrincipal(GestorOpciones gestor) {
            gestorOpciones = gestor;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            //Guardar en el fichero y salir
            gestorOpciones.salir();
        }
    }

    class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();

            switch (texto){
                case "Dar de alta un cliente":
                    ventanaAltaCliente();
                    break;
                case "Borrar un cliente":
                    ventanaBorrarCliente();
                    break;
                case "Cambiar la tarifa":
                    ventanaCambiarTarifa();
                    break;
                case "Ver un cliente":
                    ventanaVerCliente();
                    break;
                case "Ver todos los clientes":
                    //listar los clientes y pasar la lista a la ventana
                    //List<Cliente> clientes = controlador.verTodosClientes();
                    ventanaVerTodosClientes(/*clientes*/);
                    break;
                case "Dar de alta una llamada":
                    ventanaAltaLlamada();
                    break;
                case "Ver las llamadas de un cliente":
                    ventanaVerLlamadas();
                    break;
                case "Emitir una factura":
                    ventanaEmitirFactura();
                    break;
                case "Ver una factura":
                    ventanaVerFactura();
                    break;
                case "Ver las facturas de un cliente":
                    ventanaVerFacturasCliente();
                    break;
                case "Ver los clientes dados de alta entre dos fechas":
                    ventanaVerClientesFechas();
                    break;
                case "Ver llamadas entre dos fechas":
                    ventanaVerLlamadasFechas();
                    break;
                case "Ver las facturas de un cliente entre dos fechas":
                    ventanaVerFacturasFechas();
                    break;

            }
        }

    }

}
