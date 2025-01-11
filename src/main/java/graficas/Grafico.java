package graficas;

import modelo.*;

import javax.swing.*;
import java.util.ArrayList;

public interface Grafico {
    JPanel crearGrafico(ArrayList<WeatherData> datos);
}
