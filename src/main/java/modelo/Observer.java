package modelo;

import java.util.ArrayList;

public interface Observer {
    void update();
    void print();
    ArrayList<WeatherData> getDatos();
    String getNombre();
}
