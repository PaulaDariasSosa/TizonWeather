package vista;

import controlador.Controlador;

public class interfazVentana implements Vista {
    private Controlador controlador;
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void arranca() {
        System.out.println("Arrancando la vista gráfica...");
        WeatherAppPanel panel = new WeatherAppPanel();
        panel.run(controlador.getModelo());
    }
}
