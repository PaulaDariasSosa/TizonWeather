package vista;

import controlador.Controlador;
import modelo.*;

import java.util.Scanner;

public class interfazTextual implements Vista {
    private Controlador controlador;
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void arranca() {
        System.out.println("Arrancando la vista textual...");
        Modelo modelo = new Modelo();

        while (true) {
            modelo.update();
            System.out.println("Estaciones meteorológicas:");
            modelo.printNombres();

            System.out.println("Elija dos estaciones para comparar:");
            Scanner scanner = new Scanner(System.in);
            Integer estacion1Int = scanner.nextInt();
            Integer estacion2Int = scanner.nextInt();
            Estacion estacion1 = (Estacion) modelo.getEstaciones().get(estacion1Int - 1);
            Estacion estacion2 = (Estacion) modelo.getEstaciones().get(estacion2Int - 1);

            System.out.println("Elija que desea comparar:");
            System.out.println("1. Temperatura maxima y minima");
            System.out.println("2. Dirección y velocidad del viento");
            System.out.println("3. Estado del cielo");
            System.out.println("4. Obtener prevision entera para la semana");
            System.out.println("5. Obtener prevision entera para hoy");
            int opcion = scanner.nextInt();

            System.out.println("Comparando " + estacion1.getNombre() + " y " + estacion2.getNombre());
            switch (opcion) {
                case 1:
                    System.out.println("Temperatura maxima y minima");
                    WeatherData weatherData1 = estacion1.getDatos().get(0);
                    WeatherData weatherData2 = estacion2.getDatos().get(0);
                    System.out.println("En " + estacion1.getNombre() + " la temperatura máxima es " + weatherData1.getTemperaturaMaxima() + " y la mínima es " + weatherData1.getTemperaturaMinima());
                    System.out.println("En " + estacion2.getNombre() + " la temperatura máxima es " + weatherData2.getTemperaturaMaxima() + " y la mínima es " + weatherData2.getTemperaturaMinima());
                    break;
                case 2:
                    System.out.println("Dirección y velocidad del viento");
                    weatherData1 = estacion1.getDatos().get(0);
                    weatherData2 = estacion2.getDatos().get(0);
                    System.out.println("En " + estacion1.getNombre() + " la dirección y la velocidad del viento es " + weatherData1.getViento());
                    System.out.println("En " + estacion2.getNombre() + " la dirección y la velocidad del viento es " + weatherData2.getViento());
                    break;
                case 3:
                    System.out.println("Estado del cielo");
                    weatherData1 = estacion1.getDatos().get(0);
                    weatherData2 = estacion2.getDatos().get(0);
                    System.out.println("En " + estacion1.getNombre() + " el estado del cielo es " + weatherData1.getEstadoCielo());
                    System.out.println("En " + estacion2.getNombre() + " el estado del cielo es " + weatherData2.getEstadoCielo());
                    break;
                case 4:
                    System.out.println("Obtener prevision entera para la semana");
                    System.out.println("En " + estacion1.getNombre());
                    estacion1.print();
                    System.out.println("En " + estacion2.getNombre());
                    estacion2.print();

                    break;
                case 5:
                    System.out.println("Obtener prevision entera para hoy");
                    weatherData1 = estacion1.getDatos().get(0);
                    weatherData2 = estacion2.getDatos().get(0);
                    System.out.println("En " + estacion1.getNombre());
                    weatherData1.print();
                    System.out.println("En " + estacion2.getNombre());
                    weatherData2.print();
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }

    }
}
