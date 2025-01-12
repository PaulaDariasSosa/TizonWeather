package estados;

public class Chubascos implements Estado {
    @Override
    public void mostrarEstado() {
        System.out.println("Chubascos");
    }

    @Override
    public void cambiarEstado() {
        System.out.println("Cambiando a estado chubascos");
    }

    @Override
    public String getRuta() {
        return "src/main/java/imagenes/chubasco.png";
    }

    @Override
    public String getDescripcion() {
        return "Chubascos";
    }

}
