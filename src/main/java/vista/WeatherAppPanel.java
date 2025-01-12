package vista;

import graficas.*;
import modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WeatherAppPanel {
    private static JPanel mainPanel1;
    private static JLabel mainWeatherLabel1;
    private static ArrayList<JPanel> smallPanels1;

    private static JPanel mainPanel2;
    private static JLabel mainWeatherLabel2;
    private static ArrayList<JPanel> smallPanels2;

    private static JComboBox<String> stationSelector1;
    private static JComboBox<String> stationSelector2;

    private static JComboBox<String> tipos_graficos1;
    private static JComboBox<String> tipos_graficos2;

    private static Estacion estacion1;
    private static Estacion estacion2;

    private static Integer intGrafica1;
    private static Integer intGrafica2;

    //private static ArrayList<Estacion> estaciones;

    public static void run(Modelo modelo) {
        SwingUtilities.invokeLater(() -> {

            estacion1 = new Estacion(38038, "Santa Cruz de Tenerife");
            estacion2 = new Estacion(38038, "Santa Cruz de Tenerife");

            // Menú desplegable para seleccionar la estación 1
            stationSelector1 = new JComboBox<>();
            for (Observer estacion : modelo.getEstaciones()) {
                stationSelector1.addItem(estacion.getNombre());
            }

            JFrame frame = new JFrame("Weather App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1600, 800);

            // Panel principal
            JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));

            // Panel izquierdo: Predicciones de ciudades
            JPanel cityPanel = new JPanel(new GridLayout(2, 1, 10, 10));
            // Panel de gráficas
            JPanel graphPanel = new JPanel(new GridLayout(2, 1, 10, 10));

            ArrayList<Grafico> graficas = new ArrayList<>();
            graficas.add(new GraficoTemperaturas());
            graficas.add(new GraficoViento());
            graficas.add(new GraficoEstadoCielo());
            graficas.add(new GraficoTemperaturaViento());
            graficas.add(new BrujulaConFlecha());

            stationSelector1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    modelo.update();
                    int selectedIndex = stationSelector1.getSelectedIndex();
                    updateWeatherViewSuperior((Estacion) modelo.getEstaciones().get(selectedIndex));
                    estacion1 = (Estacion) modelo.getEstaciones().get(selectedIndex);
                    JPanel graph1Panel = graficas.get(intGrafica1).crearGrafico(estacion1.getDatos());
                    JPanel GraphContainer1 = new JPanel(new BorderLayout());
                    GraphContainer1.add(tipos_graficos1, BorderLayout.NORTH); // Agregar JComboBox arriba
                    GraphContainer1.add(graph1Panel, BorderLayout.CENTER);
                    graphPanel.remove(0);
                    graphPanel.add(GraphContainer1,0);
                    graphPanel.revalidate();
                    graphPanel.repaint();
                }
            });

            // Menú desplegable para seleccionar la estación 2
            stationSelector2 = new JComboBox<>();
            for (Observer estacion : modelo.getEstaciones()) {
                stationSelector2.addItem(estacion.getNombre());
            }

            stationSelector2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    modelo.update();
                    int selectedIndex = stationSelector2.getSelectedIndex();
                    updateWeatherViewInferior((Estacion) modelo.getEstaciones().get(selectedIndex));
                    estacion2 = (Estacion) modelo.getEstaciones().get(selectedIndex);
                    JPanel graph2Panel = graficas.get(intGrafica2).crearGrafico(estacion2.getDatos());
                    JPanel GraphContainer2 = new JPanel(new BorderLayout());
                    GraphContainer2.add(tipos_graficos2, BorderLayout.NORTH); // Agregar JComboBox arriba
                    GraphContainer2.add(graph2Panel, BorderLayout.CENTER);
                    graphPanel.remove(1);
                    graphPanel.add(GraphContainer2,1);
                    graphPanel.revalidate();
                    graphPanel.repaint();
                }
            });

            configureMainPanelSuperior();
            configureMainPanelInferior();
            frame.add(mainPanel, BorderLayout.CENTER);

            JPanel info =  updateWeatherViewSuperior(estacion1);
            // Crear un contenedor que combine stationSelector1 y el panel info
            JPanel infoContainer = new JPanel(new BorderLayout());
            infoContainer.add(stationSelector1, BorderLayout.NORTH); // Agregar JComboBox arriba
            infoContainer.add(info, BorderLayout.CENTER); // Agregar el panel de información aba
            cityPanel.add(infoContainer);


            JPanel info2 =  updateWeatherViewInferior(estacion2);
            // Crear un contenedor que combine stationSelector2 y el panel info
            JPanel infoContainer2 = new JPanel(new BorderLayout());
            infoContainer2.add(stationSelector2, BorderLayout.NORTH); // Agregar JComboBox arriba
            infoContainer2.add(info2, BorderLayout.CENTER);
            cityPanel.add(infoContainer2);

            // Panel derecho: Gráficas
            /// #######################################################################################################
            tipos_graficos1 = new JComboBox<>(new String[]{"Temperaturas", "Viento","Estado del cielo","Temperatura y Viento","Brújula de dirección de viento"});
            tipos_graficos2 = new JComboBox<>(new String[]{"Temperaturas", "Viento","Estado del cielo","Temperatura y Viento","Brújula de dirección de viento"});
            // se deben crear las graficas dinamicamente al selecionar una ciudad
            // Gráfica 1
            JPanel graph1Panel = createGraphPanel(new Estacion(38038, "Santa Cruz de Tenerife"));
            JPanel GraphContainer1 = new JPanel(new BorderLayout());
            GraphContainer1.add(tipos_graficos1, BorderLayout.NORTH); // Agregar JComboBox arriba
            GraphContainer1.add(graph1Panel, BorderLayout.CENTER);
            graphPanel.add(GraphContainer1);
            intGrafica1 = 0;

            // Gráfica 2
            JPanel graph2Panel = createGraphPanel(new Estacion(38038, "Santa Cruz de Tenerife"));

            JPanel GraphContainer2 = new JPanel(new BorderLayout());
            GraphContainer2.add(tipos_graficos2, BorderLayout.NORTH); // Agregar JComboBox arriba
            GraphContainer2.add(graph2Panel, BorderLayout.CENTER);
            graphPanel.add(GraphContainer2);
            intGrafica2 = 0;

            tipos_graficos1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = tipos_graficos1.getSelectedIndex();
                    intGrafica1 = selectedIndex;
                    JPanel graph1Panel = graficas.get(selectedIndex).crearGrafico(estacion1.getDatos());
                    JPanel GraphContainer1 = new JPanel(new BorderLayout());
                    GraphContainer1.add(tipos_graficos1, BorderLayout.NORTH); // Agregar JComboBox arriba
                    GraphContainer1.add(graph1Panel, BorderLayout.CENTER);
                    graphPanel.remove(0);
                    graphPanel.add(GraphContainer1,0);
                    graphPanel.revalidate();
                    graphPanel.repaint();
                }
            });

            tipos_graficos2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = tipos_graficos2.getSelectedIndex();
                    // updateWeatherView(graficas.get(selectedIndex));
                    intGrafica2 = selectedIndex;
                    JPanel graph2Panel = graficas.get(selectedIndex).crearGrafico(estacion2.getDatos());
                    JPanel GraphContainer2 = new JPanel(new BorderLayout());
                    GraphContainer2.add(tipos_graficos2, BorderLayout.NORTH); // Agregar JComboBox arriba
                    GraphContainer2.add(graph2Panel, BorderLayout.CENTER);
                    graphPanel.remove(1);
                    graphPanel.add(GraphContainer2,1);
                    graphPanel.revalidate();
                    graphPanel.repaint();
                }
            });

            // Agregar los paneles al panel principal
            mainPanel.add(cityPanel);
            mainPanel.add(graphPanel);

            // Configuración final
            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }


    private static JPanel createGraphPanel(Estacion graphTitle) {
        // Panel para un gráfico
        JPanel graphPanel = new JPanel(new BorderLayout(10, 10));

        // Crear una gráfica de ejemplo (puedes cambiarla por la que necesites)
        GraficoTemperaturas graficoTemperaturas = new GraficoTemperaturas();
        JPanel grafico = graficoTemperaturas.crearGrafico(graphTitle.getDatos());
        graphPanel.add(grafico, BorderLayout.CENTER);

        return graphPanel;
    }

    private static JPanel updateWeatherViewSuperior(Estacion estacion) {
        // Actualizar datos desde la estación
        estacion.update(); // Obtener los datos desde la API
        ArrayList<WeatherData> weatherDataList = estacion.getDatos();
        if (weatherDataList.isEmpty()) {
            mainWeatherLabel1.setText("No hay datos disponibles");
            mainWeatherLabel1.setIcon(null); // Quitar cualquier imagen previa
            for (JPanel panel : smallPanels1) {
                panel.removeAll();
                panel.add(new JLabel("-"));
            }
            return mainPanel1;
        }

        // Mostrar el clima del día actual (primer elemento de weatherDataList)
        WeatherData today = weatherDataList.get(0);
        mainWeatherLabel1.setText("<html><b>" + today.getFecha() + "</b><br>" + today.getEstadoCielo() + "</b><br>" + today.getViento() + "</b><br>" + today.getTemperaturaMaxima() + "°C - " + today.getTemperaturaMinima() + "°C"+ "</html>");
        mainWeatherLabel1.setHorizontalTextPosition(SwingConstants.CENTER); // Centra el texto respecto a la imagen
        mainWeatherLabel1.setVerticalTextPosition(SwingConstants.BOTTOM); // Texto debajo de la imagen

        // Configurar la imagen del día principal
        String iconPath = today.getEstado().getRuta(); // Método que devuelve la ruta de la imagen
        if (iconPath != null) {
            ImageIcon icon = new ImageIcon(iconPath);
            Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Ajustar tamaño
            mainWeatherLabel1.setIcon(new ImageIcon(scaledImage));
        } else {
            mainWeatherLabel1.setIcon(null); // Si no hay imagen, no mostrar nada
        }

        // Mostrar el clima de los próximos días (siguientes elementos de weatherDataList)
        for (int i = 1; i <= 6; i++) {
            JPanel panel = smallPanels1.get(i - 1);
            panel.removeAll();
            if (i < weatherDataList.size()) {
                WeatherData nextDay = weatherDataList.get(i);
                JLabel label = new JLabel("<html><b>" + nextDay.getFecha() + "</b><br>" + nextDay.getEstadoCielo() + "</b><br>" + nextDay.getViento() + "</b><br>" + nextDay.getTemperaturaMaxima() + "°C - " + nextDay.getTemperaturaMinima() + "°C"+ "</html>", SwingConstants.CENTER);

                // Agregar imagen para los días pequeños si es necesario
                String nextDayIconPath = nextDay.getEstado().getRuta();
                if (nextDayIconPath != null) {
                    ImageIcon nextDayIcon = new ImageIcon(nextDayIconPath);
                    Image scaledNextDayImage = nextDayIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(scaledNextDayImage));
                    label.setHorizontalTextPosition(SwingConstants.CENTER);
                    label.setVerticalTextPosition(SwingConstants.BOTTOM);
                }

                panel.add(label, BorderLayout.CENTER);
            } else {
                panel.add(new JLabel("-", SwingConstants.CENTER));
            }
        }

        // Refrescar la interfaz gráfica
        mainPanel1.revalidate();
        mainPanel1.repaint();
        return mainPanel1;
    }


    private static void configureMainPanelSuperior() {
        mainPanel1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Mayor espaciado entre celdas

        // Configuración para que los componentes se expandan proporcionalmente
        gbc.weightx = 1.0; // Expandir horizontalmente
        gbc.weighty = 1.0; // Expandir verticalmente
        gbc.fill = GridBagConstraints.BOTH; // Expandir para llenar el espacio

        // Etiqueta grande principal (ocupando dos filas y dos columnas)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2; // Ocupa dos filas
        gbc.gridwidth = 2; // Ocupa dos columnas
        mainWeatherLabel1 = new JLabel("", SwingConstants.CENTER);
        mainWeatherLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde más grueso
        mainWeatherLabel1.setFont(new Font("Arial", Font.BOLD, 18)); // Tamaño de fuente más grande
        mainPanel1.add(mainWeatherLabel1, gbc);

        // Crear paneles pequeños para los días siguientes
        smallPanels1 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            JPanel smallPanel = new JPanel(new BorderLayout());
            smallPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde más grueso
            smallPanels1.add(smallPanel);
        }

        // Agregar el segundo, tercer y cuarto días (fila superior, columnas 2, 3 y 4)
        for (int i = 0; i < 3; i++) {
            gbc.gridx = i + 2; // Columnas 2, 3 y 4
            gbc.gridy = 0; // Fila superior
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            mainPanel1.add(smallPanels1.get(i), gbc);
        }

        // Agregar el quinto, sexto y séptimo días (fila inferior, columnas 2, 3 y 4)
        for (int i = 3; i < 6; i++) {
            gbc.gridx = (i - 3) + 2; // Columnas 2, 3 y 4
            gbc.gridy = 1; // Fila inferior
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            mainPanel1.add(smallPanels1.get(i), gbc);
        }
    }

    private static JPanel updateWeatherViewInferior(Estacion estacion) {
        // Actualizar datos desde la estación
        estacion.update(); // Obtener los datos desde la API
        ArrayList<WeatherData> weatherDataList = estacion.getDatos();
        if (weatherDataList.isEmpty()) {
            mainWeatherLabel2.setText("No hay datos disponibles");
            mainWeatherLabel2.setIcon(null); // Quitar cualquier imagen previa
            for (JPanel panel : smallPanels2) {
                panel.removeAll();
                panel.add(new JLabel("-"));
            }
            return mainPanel2;
        }

        // Mostrar el clima del día actual (primer elemento de weatherDataList)
        WeatherData today = weatherDataList.get(0);
        mainWeatherLabel2.setText("<html><b>" + today.getFecha() + "</b><br>" + today.getEstadoCielo() + "</b><br>" + today.getViento() + "</b><br>" + today.getTemperaturaMaxima() + "°C - " + today.getTemperaturaMinima() + "°C"+ "</html>");
        mainWeatherLabel2.setHorizontalTextPosition(SwingConstants.CENTER); // Centra el texto respecto a la imagen
        mainWeatherLabel2.setVerticalTextPosition(SwingConstants.BOTTOM); // Texto debajo de la imagen

        // Configurar la imagen del día principal
        String iconPath = today.getEstado().getRuta(); // Método que devuelve la ruta de la imagen
        if (iconPath != null) {
            ImageIcon icon = new ImageIcon(iconPath);
            Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Ajustar tamaño
            mainWeatherLabel2.setIcon(new ImageIcon(scaledImage));
        } else {
            mainWeatherLabel2.setIcon(null); // Si no hay imagen, no mostrar nada
        }

        // Mostrar el clima de los próximos días (siguientes elementos de weatherDataList)
        for (int i = 1; i <= 6; i++) {
            JPanel panel = smallPanels2.get(i - 1);
            panel.removeAll();
            if (i < weatherDataList.size()) {
                WeatherData nextDay = weatherDataList.get(i);
                JLabel label = new JLabel("<html><b>" + nextDay.getFecha() + "</b><br>" + nextDay.getEstadoCielo() + "</b><br>" + nextDay.getViento() + "</b><br>" + nextDay.getTemperaturaMaxima() + "°C - " + nextDay.getTemperaturaMinima() + "°C"+ "</html>", SwingConstants.CENTER);

                // Agregar imagen para los días pequeños si es necesario
                String nextDayIconPath = nextDay.getEstado().getRuta();
                if (nextDayIconPath != null) {
                    ImageIcon nextDayIcon = new ImageIcon(nextDayIconPath);
                    Image scaledNextDayImage = nextDayIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(scaledNextDayImage));
                    label.setHorizontalTextPosition(SwingConstants.CENTER);
                    label.setVerticalTextPosition(SwingConstants.BOTTOM);
                }

                panel.add(label, BorderLayout.CENTER);
            } else {
                panel.add(new JLabel("-", SwingConstants.CENTER));
            }
        }

        // Refrescar la interfaz gráfica
        mainPanel2.revalidate();
        mainPanel2.repaint();
        return mainPanel2;
    }


    private static void configureMainPanelInferior() {
        mainPanel2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Mayor espaciado entre celdas

        // Configuración para que los componentes se expandan proporcionalmente
        gbc.weightx = 1.0; // Expandir horizontalmente
        gbc.weighty = 1.0; // Expandir verticalmente
        gbc.fill = GridBagConstraints.BOTH; // Expandir para llenar el espacio

        // Etiqueta grande principal (ocupando dos filas y dos columnas)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2; // Ocupa dos filas
        gbc.gridwidth = 2; // Ocupa dos columnas
        mainWeatherLabel2 = new JLabel("", SwingConstants.CENTER);
        mainWeatherLabel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde más grueso
        mainWeatherLabel2.setFont(new Font("Arial", Font.BOLD, 28)); // Tamaño de fuente más grande
        mainPanel2.add(mainWeatherLabel2, gbc);

        // Crear paneles pequeños para los días siguientes
        smallPanels2 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            JPanel smallPanel = new JPanel(new BorderLayout());
            smallPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde más grueso
            smallPanels2.add(smallPanel);
        }

        // Agregar el segundo, tercer y cuarto días (fila superior, columnas 2, 3 y 4)
        for (int i = 0; i < 3; i++) {
            gbc.gridx = i + 2; // Columnas 2, 3 y 4
            gbc.gridy = 0; // Fila superior
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            mainPanel2.add(smallPanels2.get(i), gbc);
        }

        // Agregar el quinto, sexto y séptimo días (fila inferior, columnas 2, 3 y 4)
        for (int i = 3; i < 6; i++) {
            gbc.gridx = (i - 3) + 2; // Columnas 2, 3 y 4
            gbc.gridy = 1; // Fila inferior
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            mainPanel2.add(smallPanels2.get(i), gbc);
        }
    }

}
