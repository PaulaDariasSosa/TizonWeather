package graficas;

import modelo.WeatherData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GraficoViento implements Grafico {
    public void mostrarGrafico(ArrayList<WeatherData> datos) {
        HashMap<String, Integer> contadorViento = new HashMap<>();

        for (WeatherData data : datos) {
            String viento = data.getViento().split(" ")[0]; // Asume que la dirección es la primera palabra
            contadorViento.put(viento, contadorViento.getOrDefault(viento, 0) + 1);
        }

        DefaultPieDataset dataset = new DefaultPieDataset();

        for (String direccion : contadorViento.keySet()) {
            dataset.setValue(direccion, contadorViento.get(direccion));
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Dirección del Viento",
                dataset,
                true, true, false
        );

        ChartPanel panel = new ChartPanel(pieChart);
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(panel);
        ventana.setSize(800, 600);
        ventana.setVisible(true);
    }

    @Override
    public JPanel crearGrafico(ArrayList<WeatherData> datos) {
        HashMap<String, Integer> contadorViento = new HashMap<>();

        for (WeatherData data : datos) {
            String viento = data.getViento().split(" ")[0]; // Asume que la dirección es la primera palabra
            contadorViento.put(viento, contadorViento.getOrDefault(viento, 0) + 1);
        }

        DefaultPieDataset dataset = new DefaultPieDataset();

        for (String direccion : contadorViento.keySet()) {
            dataset.setValue(direccion, contadorViento.get(direccion));
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Dirección del Viento",
                dataset,
                true, true, false
        );

        return new ChartPanel(pieChart);
    }
}

