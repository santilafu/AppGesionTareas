package vista;

import datos.ComponenteGestorXML;
import modelo.BaseDatosXML;
import modelo.Tarea;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AppGestionTareas extends JFrame {

    // Componentes del sistema
    private ComponenteGestorXML gestorXML;
    private BaseDatosXML baseDatos;

    // Elementos visuales
    private JTextField inputTarea;
    private JComboBox<String> comboPrioridad;
    private JTextArea areaListado;
    private JLabel barraEstado;

    public AppGestionTareas() {
        // 1. Inicializamos los componentes de datos
        gestorXML = new ComponenteGestorXML();
        baseDatos = gestorXML.cargarDatos(); // Cargar datos al inicio

        // 2. Configuración UI
        configurarVentana();
        inicializarUI();
        listarTareas(); // Mostrar lo que haya cargado
    }

    private void configurarVentana() {
        setTitle("Gestor Empresarial - Módulo XML");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Estilo nativo
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
    }

    private void inicializarUI() {
        // --- HEADER ---
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 102, 204)); // Azul Corporativo
        JLabel titulo = new JLabel("Sistema de Tareas (XML DB)");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.add(titulo);
        add(header, BorderLayout.NORTH);

        // --- PANEL CENTRAL (Formulario + Lista) ---
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Formulario de Alta
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder(" Nueva modelo.Tarea "));

        inputTarea = new JTextField();
        comboPrioridad = new JComboBox<>(new String[]{"Alta", "Media", "Baja"});
        JButton btnAgregar = new JButton("Guardar modelo.Tarea");
        btnAgregar.setBackground(new Color(204, 229, 255));

        panelForm.add(new JLabel("Descripción:")); panelForm.add(inputTarea);
        panelForm.add(new JLabel("Prioridad:")); panelForm.add(comboPrioridad);
        panelForm.add(new JLabel("")); panelForm.add(btnAgregar);

        // Área de Listado (Lectura)
        areaListado = new JTextArea();
        areaListado.setEditable(false);
        areaListado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaListado);
        scroll.setBorder(BorderFactory.createTitledBorder(" Registros en XML "));

        panelCentral.add(panelForm, BorderLayout.NORTH);
        panelCentral.add(scroll, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        // --- FOOTER ---
        barraEstado = new JLabel(" Sistema conectado. DB: bbdd_tareas.xml");
        barraEstado.setBorder(BorderFactory.createEtchedBorder());
        add(barraEstado, BorderLayout.SOUTH);

        // --- EVENTO ---
        btnAgregar.addActionListener(e -> accionGuardar());
    }

    private void accionGuardar() {
        String desc = inputTarea.getText();
        String prio = (String) comboPrioridad.getSelectedItem();

        if (desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escribe una descripción");
            return;
        }

        // 1. Crear el objeto
        Tarea nueva = new Tarea(desc, "Pendiente", prio);

        // 2. Modificar la "memoria"
        baseDatos.agregarTarea(nueva);

        // 3. Persistir usando el componente XML
        gestorXML.guardarCambios(baseDatos);

        // 4. Actualizar UI
        inputTarea.setText("");
        listarTareas();
        barraEstado.setText(" Registro guardado exitosamente en XML.");
    }

    private void listarTareas() {
        areaListado.setText("");
        List<Tarea> lista = baseDatos.getListaTareas();
        if (lista.isEmpty()) {
            areaListado.setText("No hay tareas registradas en la base de datos.");
        } else {
            for (Tarea t : lista) {
                areaListado.append(" • " + t.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppGestionTareas().setVisible(true));
    }
}