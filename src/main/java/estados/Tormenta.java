package estados;

public class Tormenta implements Estado {
    public void mostrarEstado() {
        System.out.println("Tormenta");
    }

    public void cambiarEstado() {
        System.out.println("Cambiando a estado tormenta");
    }

    public String getRuta() {
        return "src/main/java/imagenes/tormenta.png";
    }

    public String getDescripcion() {
        return "Tormenta";
    }


}
