package graficas;

import modelo.WeatherData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GraficoEstadoCielo implements Grafico {
    public void mostrarGrafico(ArrayList<WeatherData> datos) {
        HashMap<String, Integer> contadorEstados = new HashMap<>();

        for (WeatherData data : datos) {
            String estado = data.getEstadoCielo();
            contadorEstados.put(estado, contadorEstados.getOrDefault(estado, 0) + 1);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String estado : contadorEstados.keySet()) {
            dataset.addValue(contadorEstados.get(estado), "Días", estado);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Estado del Cielo",              // Título del gráfico
                "Estado",                        // Etiqueta del eje X
                "Número de días",                // Etiqueta del eje Y
                dataset,                         // Conjunto de datos
                PlotOrientation.VERTICAL,        // Orientación del gráfico
                true,                            // Mostrar leyenda
                true,                            // Mostrar tooltips
                false                            // URLs configuradas
        );

        ChartPanel panel = new ChartPanel(barChart);
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(panel);
        ventana.setSize(800, 600);
        ventana.setVisible(true);
    }

    @Override
    public JPanel crearGrafico(ArrayList<WeatherData> datos) {
        HashMap<String, Integer> contadorEstados = new HashMap<>();

        for (WeatherData data : datos) {
            String estado = data.getEstadoCielo();
            contadorEstados.put(estado, contadorEstados.getOrDefault(estado, 0) + 1);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String estado : contadorEstados.keySet()) {
            dataset.addValue(contadorEstados.get(estado), "Días", estado);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Estado del Cielo",              // Título del gráfico
                "Estado",                        // Etiqueta del eje X
                "Número de días",                // Etiqueta del eje Y
                dataset,                         // Conjunto de datos
                PlotOrientation.VERTICAL,        // Orientación del gráfico
                true,                            // Mostrar leyenda
                true,                            // Mostrar tooltips
                false                            // URLs configuradas
        );

        return new ChartPanel(barChart);
    }
}

