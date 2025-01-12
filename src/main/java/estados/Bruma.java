package estados;

public class Bruma implements Estado {
    public void mostrarEstado() {
        System.out.println("Bruma");
    }

    public void cambiarEstado() {
        System.out.println("Cambiando a estado bruma");
    }

    public String getRuta() {
        return "src/main/java/imagenes/bruma.png";
    }

    public String getDescripcion() {
        return "Bruma";
    }
}
