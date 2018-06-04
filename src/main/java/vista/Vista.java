package vista;

import com.sun.javaws.util.JfxHelper;
import controlador.Controlador;
import controlador.gestor.GestorOpciones;
import modelo.Modelo;
import modelo.atributos.Direccion;
import modelo.clientes.Cliente;
import modelo.clientes.Empresa;
import modelo.clientes.Particular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Vista implements InterfazVista {
    private Controlador controlador;
    private Modelo modelo;
    private boolean esParticular;
    //Añadir botones y todo eso

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
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

    public void creaGUI(GestorOpciones gestor) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                opcionCLF(gestor);
            }
        });
    }


    private void opcionCLF(GestorOpciones gestor){
        JFrame ventana = new JFrame("Ventana Principal");
        Container contenedor = ventana.getContentPane();
        Toolkit screen = Toolkit.getDefaultToolkit();
        ventana.setSize(screen.getScreenSize().width/2,screen.getScreenSize().height/5);
        ventana.setLocation(screen.getScreenSize().width/4, screen.getScreenSize().height/3);
        JTextField elegirOpcion = new JTextField("Elige una opcion:");
        elegirOpcion.setEditable(false);
        JButton clientes = new JButton("Clientes");
        JButton llamadas = new JButton("LLamadas");
        JButton facturas = new JButton("Facturas");

        clientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton boton = (JButton) e.getSource();
                String opcion = boton.getText();
                switch (opcion){
                    case "Clientes":
                        //abrir la ventana de clientes
                        ventanaClientes(gestor);
                        ventana.setVisible(false);
                        break;
                    case "Llamadas":
                        //abrir la ventana de llamadas
                        ventanaLlamadas(gestor);
                        ventana.setVisible(false);
                        break;
                    case "Facturas":
                        //abrir la ventana de Facturas
                        ventanaFacturas(gestor);
                        ventana.setVisible(false);
                        break;
                }
            }
        });
        llamadas.addActionListener(clientes.getActionListeners()[0]);
        facturas.addActionListener(clientes.getActionListeners()[0]);
        //texto de opcion:
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        elegirOpcion.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(elegirOpcion);
        //botones
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        clientes.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(clientes);
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        llamadas.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(llamadas);
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        facturas.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(facturas);

        ventana.addWindowListener(new VentanaPrincipal(gestor));
        //ventana.pack();
        ventana.setVisible(true);
    }


    private void ventanaClientes(GestorOpciones gestor){
        JFrame ventana = new JFrame();
        Toolkit screen = Toolkit.getDefaultToolkit();
        ventana.setLocation(screen.getScreenSize().width/3, screen.getScreenSize().height/6);
        JPanel panel = new JPanel();
        JTabbedPane pestañas = new JTabbedPane();
        JPanel altaCliente = new JPanel();
        JPanel borrarCliente = new JPanel();
        JPanel verCliente = new JPanel();
        JPanel verTodosClientes = new JPanel();
        JPanel cambiarTarifa = new JPanel();
        JPanel verClienteEntreFechas = new JPanel();

        //---------------------------
        //-------Alta Cliente--------
        //---------------------------
        esParticular = false;
        altaCliente.setLayout(new BoxLayout(altaCliente, BoxLayout.PAGE_AXIS));
        JPanel jpAlta = new JPanel();
        JTextArea areaAlta = new JTextArea(8, 35);
        JScrollPane scrollAlta = new JScrollPane(areaAlta);
        areaAlta.setEditable(false);
        jpAlta.add(scrollAlta);
        JRadioButton empresa = new JRadioButton("Empresa");
        empresa.setActionCommand("empresa");
        JRadioButton particular = new JRadioButton("Particular");
        particular.setActionCommand("particular");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(empresa);
        grupo.add(particular);
        JTextField nombre = new JTextField(20);
        JLabel nombreLabel = new JLabel("(*)Nombre:");
        JTextField apellidos = new JTextField(20);
        JLabel apellidosLabel = new JLabel("Apellidos:");
        JTextField dni = new JTextField(20);
        JLabel dniLabel = new JLabel("(*)DNI/CIF:");
        JTextField codpostal = new JTextField(20);
        JLabel codpostalLabel = new JLabel("Codigo Postal:");
        JTextField provincia = new JTextField(20);
        JLabel provinciaLabel = new JLabel("Provincia:");
        JTextField poblacion = new JTextField(20);
        JLabel poblacionLabel = new JLabel("Poblacion:");
        JTextField email = new JTextField(20);
        JLabel emailLabel = new JLabel("E-mail:");
        JPanel datosAlta = new JPanel();
        datosAlta.setLayout(new BoxLayout(datosAlta, BoxLayout.PAGE_AXIS));

        datosAlta.add(empresa);
        datosAlta.add(particular);
        datosAlta.add(nombreLabel);
        datosAlta.add(nombre);
        datosAlta.add(apellidosLabel);
        datosAlta.add(apellidos);
        datosAlta.add(dniLabel);
        datosAlta.add(dni);
        datosAlta.add(codpostalLabel);
        datosAlta.add(codpostal);
        datosAlta.add(provinciaLabel);
        datosAlta.add(provincia);
        datosAlta.add(poblacionLabel);
        datosAlta.add(poblacion);
        datosAlta.add(emailLabel);
        datosAlta.add(email);
        altaCliente.add(datosAlta);
        altaCliente.add(jpAlta);
        empresa.setSelected(true);
        apellidosLabel.setEnabled(false);
        apellidos.setEnabled(false);

        ItemListener partempr = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JRadioButton boton = (JRadioButton) e.getSource();
                String opcion = boton.getText();
                if (e.getStateChange() == ItemEvent.SELECTED && opcion.equals("Empresa")){
                    esParticular = false;
                    apellidosLabel.setEnabled(false);
                    apellidos.setEnabled(false);
                }else if (e.getStateChange() == ItemEvent.SELECTED && opcion.equals("Particular")){
                    esParticular = true;
                    apellidosLabel.setEnabled(true);
                    apellidos.setEnabled(true);
                }
                /*switch (opcion) {
                    case "particular":
                        esParticular = true;
                        apellidos.setEnabled(true);
                        break;
                    case "empresa":
                        esParticular = false;
                        apellidos.setEnabled(false);
                        break;
                }*/
            }
        };
        empresa.addItemListener(partempr);
        particular.addItemListener(partempr);

        JButton anyadir = new JButton("Añadir");
        altaCliente.add(anyadir);
        anyadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente;
                if (esParticular) {
                    if (apellidos.getText().equals("")) return;
                    cliente = new Particular(apellidos.getText());
                }
                else{
                    cliente = new Empresa();
                }
                if (nombre.getText().equals("") || dni.getText().equals("")){
                    areaAlta.append("No se ha podido añadir el cliente:\nLos campos marcados con (*) son obligatorios\n\n");
                    return;
                }
                cliente.setNombre(nombre.getText());
                Direccion direccion = new Direccion(Integer.parseInt(codpostal.getText()), provincia.getText(), poblacion.getText());
                cliente.setDireccion(direccion);
                cliente.setDni(dni.getText());
                cliente.setEmail(email.getText());
                gestor.getMetodo().addCliente(cliente);
                areaAlta.append("Cliente añadido correctamente\n\n");
            }
        });

        //---------------------------
        //------Borrar Cliente-------
        //---------------------------
        borrarCliente.setLayout(new BoxLayout(borrarCliente, BoxLayout.PAGE_AXIS));
        JPanel jpborrado = new JPanel();
        JTextArea areaBorrado = new JTextArea(20, 35);
        JScrollPane scrollBorrado = new JScrollPane(areaBorrado);
        areaBorrado.setEditable(false);
        jpborrado.add(scrollBorrado);
        JTextField dni2 = new JTextField(20);
        JLabel dniLabel2 = new JLabel("DNI/CIF:");
        JPanel jpDatosBorrado = new JPanel();

        jpDatosBorrado.add(dniLabel2);
        jpDatosBorrado.add(dni2);
        JButton borrar = new JButton("Borrar");
        jpDatosBorrado.add(borrar);
        borrarCliente.add(jpDatosBorrado);
        borrarCliente.add(jpborrado);
        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gestor.getMetodo().removeCliente(dni2.getText());
                    areaBorrado.append("Cliente con dni: " + dni2.getText() + "\nBorrado Correctamente\n\n");
                }catch (NoSuchElementException exc){
                    areaBorrado.append("Cliente no encontrado\n\n");
                }
            }
        });


        //---------------------------
        //--------Ver Cliente--------
        //---------------------------
        verCliente.setLayout(new BoxLayout(verCliente, BoxLayout.PAGE_AXIS));
        JTextField dni3 = new JTextField(20);
        JLabel dniLabel3 = new JLabel("DNI/CIF:");
        JPanel datosVerCliente = new JPanel();
        datosVerCliente.add(dniLabel3);
        datosVerCliente.add(dni3);
        verCliente.add(datosVerCliente);

        JPanel jp = new JPanel();
        JTextArea area = new JTextArea(20, 35);
        JScrollPane jScrollPane = new JScrollPane(area);
        area.setEditable(false);
        jp.add(jScrollPane);
        verCliente.add(jp);
        JButton buscar = new JButton("Buscar");
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cliente = gestor.getMetodo().devuelveCliente(dni3.getText()).toString();
                if (cliente.equals("Optional.empty"))
                    area.append("Cliente no encontrado\n");
                else
                    area.append(cliente.replace("Optional[", ""));
                area.append("--------------------------------------------------------\n");
            }
        });
        verCliente.add(buscar);


        //----------------------------
        //---Ver Todos Los Clientes---
        //----------------------------
        verTodosClientes.setLayout(new BoxLayout(verTodosClientes, BoxLayout.PAGE_AXIS));
        JPanel panelClientes = new JPanel();
        JTextArea area1 = new JTextArea(20,35);
        area1.setEditable(false);
        JScrollPane jScrollPane1 = new JScrollPane(area1);
        panelClientes.add(jScrollPane1);
        JButton actualizar = new JButton("Actualizar");
        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Cliente> listaClientes = gestor.getMetodo().listaClientes();
                for (Cliente unCliente : listaClientes){
                    area1.append(unCliente.toString());
                }
                area1.append("-----------------------------------------------------------------------\n");
            }
        });
        verTodosClientes.add(actualizar);
        verTodosClientes.add(panelClientes);

        //----------------------------
        //-------Cambiar Tarifa-------
        //----------------------------



        //-----------------------------
        //--Ver Clientes Entre Fechas--
        //-----------------------------



        pestañas.addTab("Alta Cliente", altaCliente);
        pestañas.addTab("Borrar Cliente", borrarCliente);
        pestañas.addTab("Ver Cliente", verCliente);
        pestañas.addTab("Ver Todos Los Clientes", verTodosClientes);
        pestañas.addTab("Cambiar Tarifa", cambiarTarifa);
        pestañas.addTab("Ver Cliente entre Fechas", verClienteEntreFechas);

        panel.add(pestañas);
        ventana.setContentPane(panel);
        ventana.setVisible(true);
        ventana.pack();
        ventana.addWindowListener(new VentanaPrincipal(gestor));
    }

    private void ventanaLlamadas(GestorOpciones gestor){

    }

    private void ventanaFacturas(GestorOpciones gestor){

    }

/*
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
                    ventanaVerTodosClientes();
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
*/
}
