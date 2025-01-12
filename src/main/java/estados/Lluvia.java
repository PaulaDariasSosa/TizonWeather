package estados;

public class Lluvia implements Estado {
    @Override
    public void mostrarEstado() {
        System.out.println("Lluvia");
    }

    @Override
    public void cambiarEstado() {
        System.out.println("Cambiando a estado lluvia");
    }

    @Override
    public String getRuta() {
        return "src/main/java/imagenes/lluvia.png";
    }

    @Override
    public String getDescripcion() {
        return "Lluvia";
    }
}
