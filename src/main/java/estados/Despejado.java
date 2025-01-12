package estados;

public class Despejado implements Estado {
    @Override
    public void mostrarEstado() {
        System.out.println("Despejado");
    }

    @Override
    public void cambiarEstado() {
        System.out.println("Cambiando a estado despejado");
    }

    @Override
    public String getRuta() {
        return "src/main/java/imagenes/dom.png";
    }

    @Override
    public String getDescripcion() {
        return "Despejado";
    }
}
