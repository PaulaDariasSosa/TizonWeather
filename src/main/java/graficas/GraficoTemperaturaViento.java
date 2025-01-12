package graficas;

import modelo.WeatherData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.ArrayList;

public class GraficoTemperaturaViento implements Grafico {
    public void mostrarGrafico(ArrayList<WeatherData> datos) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (WeatherData data : datos) {
            dataset.addValue(Double.parseDouble(data.getTemperaturaMaxima()), "Temperatura Máxima", data.getFecha());
            dataset.addValue(Double.parseDouble(data.getTemperaturaMinima()), "Temperatura Mínima", data.getFecha());

            String[] vientoData = data.getViento().split(" ");
            double velocidadViento = Double.parseDouble(vientoData[vientoData.length - 2]); // Suponiendo "NNW 10 km/h"
            dataset.addValue(velocidadViento, "Viento (km/h)", data.getFecha());
        }

        JFreeChart stackedBarChart = ChartFactory.createStackedBarChart(
                "Temperatura y Viento",          // Título del gráfico
                "Fecha",                         // Etiqueta del eje X
                "Valores",                       // Etiqueta del eje Y
                dataset,                         // Conjunto de datos
                PlotOrientation.VERTICAL,        // Orientación del gráfico
                true,                            // Mostrar leyenda
                true,                            // Mostrar tooltips
                false                            // URLs configuradas
        );

        ChartPanel panel = new ChartPanel(stackedBarChart);
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(panel);
        ventana.setSize(800, 600);
        ventana.setVisible(true);
    }

    @Override
    public JPanel crearGrafico(ArrayList<WeatherData> datos) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (WeatherData data : datos) {
            dataset.addValue(Double.parseDouble(data.getTemperaturaMaxima()), "Temperatura Máxima", data.getFecha());
            dataset.addValue(Double.parseDouble(data.getTemperaturaMinima()), "Temperatura Mínima", data.getFecha());

            String[] vientoData = data.getViento().split(" ");
            double velocidadViento = Double.parseDouble(vientoData[vientoData.length - 2]); // Suponiendo "NNW 10 km/h"
            dataset.addValue(velocidadViento, "Viento (km/h)", data.getFecha());
        }

        JFreeChart stackedBarChart = ChartFactory.createStackedBarChart(
                "Temperatura y Viento",          // Título del gráfico
                "Fecha",                         // Etiqueta del eje X
                "Valores",                       // Etiqueta del eje Y
                dataset,                         // Conjunto de datos
                PlotOrientation.VERTICAL,        // Orientación del gráfico
                true,                            // Mostrar leyenda
                true,                            // Mostrar tooltips
                false                            // URLs configuradas
        );

        return new ChartPanel(stackedBarChart);
    }
}

