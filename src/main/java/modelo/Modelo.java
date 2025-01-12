package modelo;

import java.util.ArrayList;

public class Modelo {
    private ArrayList<Observer> estaciones;

    public Modelo() {
        estaciones = new ArrayList<>();
        estaciones.add(new Estacion(38038, "Santa Cruz de Tenerife"));
        estaciones.add(new Estacion(38051, "La Victoria de Acentejo"));
        estaciones.add(new Estacion(38017, "Granadilla de Abona"));
        estaciones.add(new Estacion(38001, "Adeje"));
        estaciones.add(new Estacion(38011, "Candelaria"));
        estaciones.add(new Estacion(42098, "Herrera de Soria"));
        estaciones.add(new Estacion(20069, "Donostia/San Sebastián"));

    }

    public Modelo(ArrayList<Observer> estaciones) {
        this.estaciones = estaciones;
    }

    public void addEstacion(Estacion estacion) {
        estaciones.add(estacion);
    }

    public void removeEstacion(Estacion estacion) {
        estaciones.remove(estacion);
    }

    public ArrayList<Observer> getEstaciones() {
        return estaciones;
    }

    public void print() {
        for (Observer observer : estaciones) {
            observer.print();
        }
    }

    public void update() {
        for (Observer observer : estaciones) {
            observer.update();
        }
    }

    public void printNombres() {
        int i = 1;
        for (Observer observer : estaciones) {
            System.out.println( i + ". " + observer.getNombre());
            ++i;
        }
    }


}
