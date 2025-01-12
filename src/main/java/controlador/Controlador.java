package controlador;

import modelo.Modelo;
import vista.Vista;
import vista.interfazTextual;
import vista.interfazVentana;

import java.util.Scanner;

public class Controlador {
    private static Vista vista;
    private Modelo modelo;

    public Controlador() {
        this.vista = new interfazVentana();
        this.modelo = new Modelo();
    }

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void ejecutar() { // Disparado por la Vista
        vista.arranca();
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        System.out.println("Elija el tipo de vista:");
        System.out.println("1. Vista textual");
        System.out.println("2. Vista gráfica");
        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        if (opcion == 1) {
            controlador.setVista(new interfazTextual());
        } else {
            controlador.setVista(new interfazVentana());
        }
        vista.setControlador(controlador);
        controlador.ejecutar();
    }
}
