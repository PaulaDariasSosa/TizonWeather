package estados;

public class Cubierto implements Estado {
    @Override
    public void mostrarEstado() {
        System.out.println("Cubierto");
    }

    @Override
    public void cambiarEstado() {
        System.out.println("Cambiando a estado cubierto");
    }

    @Override
    public String getRuta() {
        return "src/main/java/imagenes/nubes.png";
    }

    @Override
    public String getDescripcion() {
        return "Cubierto";
    }
}
