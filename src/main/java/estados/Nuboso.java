package estados;

public class Nuboso implements Estado {
    public void mostrarEstado() {
        System.out.println("Nuboso");
    }

    public void cambiarEstado() {
        System.out.println("Cambiando a estado nuboso");
    }

    public String getRuta() {
        return "src/main/java/imagenes/dia-nublado.png";
    }

    public String getDescripcion() {
        return "Nuboso";
    }
}
