package graficas;

import modelo.WeatherData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.ArrayList;

public class GraficoTemperaturas implements Grafico {
    public void mostrarGrafico(ArrayList<WeatherData> datos) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (WeatherData data : datos) {
            dataset.addValue(Double.parseDouble(data.getTemperaturaMaxima()), "Máxima", data.getFecha());
            dataset.addValue(Double.parseDouble(data.getTemperaturaMinima()), "Mínima", data.getFecha());
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Temperaturas Máximas y Mínimas", // Título del gráfico
                "Fecha",                         // Etiqueta del eje X
                "Temperatura (°C)",              // Etiqueta del eje Y
                dataset,                         // Conjunto de datos
                PlotOrientation.VERTICAL,        // Orientación del gráfico
                true,                            // Mostrar leyenda
                true,                            // Mostrar tooltips
                false                            // URLs configuradas
        );

        ChartPanel panel = new ChartPanel(lineChart);
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(panel);
        ventana.setSize(800, 600);
        ventana.setVisible(true);
    }
    public JPanel crearGrafico(ArrayList<WeatherData> datos) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (WeatherData data : datos) {
            dataset.addValue(Double.parseDouble(data.getTemperaturaMaxima()), "Máxima", data.getFecha());
            dataset.addValue(Double.parseDouble(data.getTemperaturaMinima()), "Mínima", data.getFecha());
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Temperaturas Máximas y Mínimas", // Título del gráfico
                "Fecha",                         // Etiqueta del eje X
                "Temperatura (°C)",              // Etiqueta del eje Y
                dataset,                         // Conjunto de datos
                PlotOrientation.VERTICAL,        // Orientación del gráfico
                true,                            // Mostrar leyenda
                true,                            // Mostrar tooltips
                false                            // URLs configuradas
        );

        return new ChartPanel(lineChart);
    }
}
