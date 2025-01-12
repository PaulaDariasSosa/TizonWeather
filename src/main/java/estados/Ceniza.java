package estados;

public class Ceniza implements Estado{
    @Override
    public void mostrarEstado() {
        System.out.println("Ceniza");
    }

    @Override
    public void cambiarEstado() {
        System.out.println("Cambiando a estado ceniza");
    }

    @Override
    public String getRuta() {
        return "src/main/java/imagenes/ceniza-volcanica.png";
    }

    @Override
    public String getDescripcion() {
        return "Ceniza";
    }
}
