package modelo;

import estados.Estado;


public class WeatherData {
    private String fecha;
    private String temperaturaMaxima;
    private String temperaturaMinima;
    private String estadoCielo; // cambiar por una clase "estado"
    private Estado estado_;
    private String viento;

    public WeatherData() {
        this.fecha = "";
        this.temperaturaMaxima = "";
        this.temperaturaMinima = "";
        this.estadoCielo = "";
        this.viento = "";
    }

    //constructor copia
    public WeatherData(WeatherData weatherData) {
        this.fecha = weatherData.fecha;
        this.temperaturaMaxima = weatherData.temperaturaMaxima;
        this.temperaturaMinima = weatherData.temperaturaMinima;
        this.estadoCielo = weatherData.estadoCielo;
        this.viento = weatherData.viento;
        if (weatherData.estado_ != null) {
            this.estado_ = weatherData.estado_;
        }
    }

    public void setWeatherData(String fecha, String temperaturaMaxima, String temperaturaMinima, String estadoCielo, String viento) {
        this.fecha = fecha;
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.estadoCielo = estadoCielo;
        this.viento = viento;
    }

    public void setWeatherData(String fecha, String temperaturaMaxima, String temperaturaMinima, String estadoCielo, String viento, Estado estado) {
        this.setWeatherData(fecha, temperaturaMaxima, temperaturaMinima, estadoCielo, viento);
        this.estado_ = estado;
    }

    public void setEstado(Estado estado) {
        estado_ = estado;
    }

    public void print() {
        System.out.println("  Fecha: " + fecha);
        System.out.println("  Temperatura Máxima: " + temperaturaMaxima + "°C");
        System.out.println("  Temperatura Mínima: " + temperaturaMinima + "°C");
        System.out.println("  Estado del Cielo: " + estadoCielo);
        System.out.println("  Viento: " + viento);
        if (estado_ != null){
            System.out.print("  Estado simplificado: ");
            estado_.mostrarEstado();
        }
        System.out.println();
    }

    public String getEstadoCielo() {
        return estadoCielo;
    }

    public String getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado_;
    }

    public String getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public String getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public String getViento() {
        return viento;
    }
}