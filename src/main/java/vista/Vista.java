package vista;

import controlador.Controlador;
import controlador.gestor.GestorOpciones;
import modelo.Modelo;
import modelo.atributos.Direccion;
import modelo.clientes.Cliente;
import modelo.clientes.Empresa;
import modelo.clientes.Particular;
import modelo.constructores.ConstructorTarifa;
import modelo.tarifa.DiaReducido;
import modelo.tarifa.Tarifa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Vista implements InterfazVista {
    private Controlador controlador;
    private Modelo modelo;
    private boolean esParticular;
    private boolean cambiaDias;
    //Añadir botones y todo eso

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void creaGUI(GestorOpciones gestor) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                opcionCLF(gestor);
            }
        });
    }

    private void opcionCLF(GestorOpciones gestor) {
        JFrame ventana = new JFrame("Ventana Principal");
        Container contenedor = ventana.getContentPane();
        Toolkit screen = Toolkit.getDefaultToolkit();
        ventana.setSize(screen.getScreenSize().width / 2, screen.getScreenSize().height / 5);
        ventana.setLocation(screen.getScreenSize().width / 4, screen.getScreenSize().height / 3);
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
                switch (opcion) {
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

    private void ventanaClientes(GestorOpciones gestor) {
        JFrame ventana = new JFrame();
        Toolkit screen = Toolkit.getDefaultToolkit();
        ventana.setLocation(screen.getScreenSize().width / 3, screen.getScreenSize().height / 6);
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
                if (e.getStateChange() == ItemEvent.SELECTED && opcion.equals("Empresa")) {
                    esParticular = false;
                    apellidosLabel.setEnabled(false);
                    apellidos.setEnabled(false);
                } else if (e.getStateChange() == ItemEvent.SELECTED && opcion.equals("Particular")) {
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
                    if (apellidos.getText().equals("")) {
                        areaAlta.append("Introduce el apellido del Particular\n\n");
                        return;
                    }
                    cliente = new Particular(apellidos.getText());
                } else {
                    cliente = new Empresa();
                }
                if (nombre.getText().equals("") || dni.getText().equals("")) {
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
                } catch (NoSuchElementException exc) {
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


        JPanel jp = new JPanel();
        JTextArea area = new JTextArea(20, 35);
        JScrollPane jScrollPane = new JScrollPane(area);
        area.setEditable(false);
        jp.add(jScrollPane);
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
        datosVerCliente.add(buscar);
        verCliente.add(datosVerCliente);
        verCliente.add(jp);


        //----------------------------
        //---Ver Todos Los Clientes---
        //----------------------------
        verTodosClientes.setLayout(new BoxLayout(verTodosClientes, BoxLayout.PAGE_AXIS));
        JPanel panelClientes = new JPanel();
        JTextArea area1 = new JTextArea(25, 35);
        area1.setEditable(false);
        JScrollPane jScrollPane1 = new JScrollPane(area1);
        panelClientes.add(jScrollPane1);
        JButton actualizar = new JButton("Actualizar");
        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Cliente> listaClientes = gestor.getMetodo().listaClientes();
                area1.setText("");
                for (Cliente unCliente : listaClientes) {
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
        cambiaDias = true;
        cambiarTarifa.setLayout(new BoxLayout(cambiarTarifa, BoxLayout.PAGE_AXIS));
        JPanel jpCambiarTarifa = new JPanel();
        JTextField dniCambiarTarifa = new JTextField(20);
        JLabel dniLabelCambiarTarifa = new JLabel("DNI/CIF:");
        JLabel precioTarifaLabel = new JLabel("Precio:");
        JTextField precioTarifa = new JTextField(20);
        JPanel precio = new JPanel();
        precio.add(precioTarifaLabel);
        precio.add(precioTarifa);
        JRadioButton porDia = new JRadioButton("Por Dias");
        porDia.setActionCommand("Por Dias");
        JRadioButton porHoras = new JRadioButton("Por Horas");
        porHoras.setActionCommand("Por Horas");
        ButtonGroup grupoCambiarTarifa = new ButtonGroup();
        grupoCambiarTarifa.add(porDia);
        grupoCambiarTarifa.add(porHoras);
        JPanel opcionPorHoras = new JPanel();
        JLabel horaInicio = new JLabel("Hora Inicio");
        JLabel horaFin = new JLabel("Hora Fin");
        JTextField hInicio = new JTextField(10);
        JTextField hFin = new JTextField(10);
        opcionPorHoras.add(horaInicio);
        opcionPorHoras.add(hInicio);
        opcionPorHoras.add(horaFin);
        opcionPorHoras.add(hFin);
        JLabel opcionDiaLabel = new JLabel("Introduce un dia de la semana(0-6)");
        JTextField opcionDia = new JTextField(10);

        jpCambiarTarifa.add(dniLabelCambiarTarifa);
        jpCambiarTarifa.add(dniCambiarTarifa);
        ItemListener diasHoras = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JRadioButton boton = (JRadioButton) e.getSource();
                String opcion = boton.getText();
                if (opcion.equals("Por Dias") && e.getStateChange() == ItemEvent.SELECTED) {
                    cambiaDias = true;
                    horaInicio.setEnabled(false);
                    hInicio.setEnabled(false);
                    horaFin.setEnabled(false);
                    hFin.setEnabled(false);
                    opcionDiaLabel.setEnabled(true);
                    opcionDia.setEnabled(true);
                } else if (opcion.equals("Por Horas") && e.getStateChange() == ItemEvent.SELECTED) {
                    cambiaDias = false;
                    opcionDiaLabel.setEnabled(false);
                    opcionDia.setEnabled(false);
                    horaInicio.setEnabled(true);
                    hInicio.setEnabled(true);
                    horaFin.setEnabled(true);
                    hFin.setEnabled(true);
                }
            }
        };
        porDia.addItemListener(diasHoras);
        porHoras.addItemListener(diasHoras);

        cambiarTarifa.add(jpCambiarTarifa);
        cambiarTarifa.add(precio);
        cambiarTarifa.add(porDia);
        porDia.setSelected(true);
        JPanel opcionDiaPanel = new JPanel();
        opcionDiaPanel.add(opcionDiaLabel);
        opcionDiaPanel.add(opcionDia);
        cambiarTarifa.add(opcionDiaPanel);
        cambiarTarifa.add(porHoras);
        cambiarTarifa.add(opcionPorHoras);
        JPanel panelCambiarTarifa = new JPanel();
        JTextArea areaCambiarTarifa = new JTextArea(10, 35);
        JScrollPane scrollCambiarTarifa = new JScrollPane(areaCambiarTarifa);
        areaCambiarTarifa.setEditable(false);
        panelCambiarTarifa.add(scrollCambiarTarifa);

        JButton cambiaTarifa = new JButton("Cambiar Tarifa");
        cambiaTarifa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente;
                Tarifa tarifa;
                ConstructorTarifa constructorTarifa = new ConstructorTarifa();
                if (gestor.getMetodo().devuelveCliente(dniCambiarTarifa.getText()).isPresent()) {
                    cliente = gestor.getMetodo().devuelveCliente(dniCambiarTarifa.getText()).get();
                    if (cambiaDias) {
                        tarifa = constructorTarifa.getInstanceDiaReducido(cliente.getTarifa(), Integer.parseInt(opcionDia.getText()), Double.parseDouble(precioTarifa.getText()));
                        cliente.setTarifa(tarifa);
                    } else {
                        tarifa = constructorTarifa.getInstanceHoraReducida(cliente.getTarifa(),Integer.parseInt(hInicio.getText()), Integer.parseInt(hFin.getText()), Double.parseDouble(precioTarifa.getText()));
                        cliente.setTarifa(tarifa);
                    }
                    areaCambiarTarifa.append("Tarifa de cliente con dni: " + dniCambiarTarifa.getText() + "\nCambiada Correctamente\n\n");
                }else{
                    areaCambiarTarifa.append("Cliente no encontrado\n\n");
                }
            }
        });
        cambiarTarifa.add(cambiaTarifa);
        cambiarTarifa.add(panelCambiarTarifa);


        //-----------------------------
        //--Ver Clientes Entre Fechas--
        //-----------------------------
        verClienteEntreFechas.setLayout(new BoxLayout(verClienteEntreFechas, BoxLayout.PAGE_AXIS));
        /*
         *
         *
         * */


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

    private void ventanaLlamadas(GestorOpciones gestor) {

    }

    private void ventanaFacturas(GestorOpciones gestor) {

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

}
