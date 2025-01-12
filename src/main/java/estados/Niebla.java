package estados;

public class Niebla implements Estado {
    public void mostrarEstado() {
        System.out.println("Niebla");
    }

    public void cambiarEstado() {
        System.out.println("Cambiando a estado niebla");
    }

    public String getRuta() {
        return "src/main/java/imagenes/foog.png";
    }

    public String getDescripcion() {
        return "Niebla";
    }
}


