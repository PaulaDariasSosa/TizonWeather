package estados;

public class Nieve implements Estado{
    public void mostrarEstado() {
        System.out.println("Nieve");
    }

    public void cambiarEstado() {
        System.out.println("Cambiando a estado nieve");
    }

    public String getRuta() {
        return "src/main/java/imagenes/nieve.png";
    }

    public String getDescripcion() {
        return "Nieve";
    }
}
