package graficas;

import modelo.WeatherData;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BrujulaConFlecha extends JPanel implements Grafico {

    private BufferedImage backgroundImage;
    private double angle;
    private boolean sinViento = false;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dibujar la imagen de fondo
        if (backgroundImage != null) {
            int x = (getWidth() - backgroundImage.getWidth()) / 2;
            int y = (getHeight() - backgroundImage.getHeight()) / 2;
            g2d.drawImage(backgroundImage, x, y, this);

            // Dibujar la flecha sobre la brújula
            if (sinViento) {
                drawCalmPoint(g2d, x + backgroundImage.getWidth() / 2, y + backgroundImage.getHeight() / 2);
            } else {
                drawArrow(g2d, x + backgroundImage.getWidth() / 2, y + backgroundImage.getHeight() / 2);
            }
        }
    }

    private void drawArrow(Graphics2D g2d, int centerX, int centerY) {
        // Longitud de la flecha
        int arrowLength = 100;

        // Configurar la rotación de la flecha
        AffineTransform oldTransform = g2d.getTransform();
        g2d.rotate(Math.toRadians(angle), centerX, centerY);

        // Dibujar la línea de la flecha
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(centerX, centerY, centerX, centerY - arrowLength);

        // Dibujar la cabeza de la flecha
        int arrowHeadSize = 10;
        Polygon arrowHead = new Polygon();
        arrowHead.addPoint(centerX, centerY - arrowLength);
        arrowHead.addPoint(centerX - arrowHeadSize, centerY - arrowLength + arrowHeadSize);
        arrowHead.addPoint(centerX + arrowHeadSize, centerY - arrowLength + arrowHeadSize);

        g2d.fill(arrowHead);

        // Restaurar la transformación original
        g2d.setTransform(oldTransform);
    }

    private double getAngulo(String direccion) {
        switch (direccion) {
            case "N": return 0;
            case "NE": return 45;
            case "E": return 90;
            case "SE": return 135;
            case "S": return 180;
            case "SO": return 225;
            case "O": return 270;
            case "NO": return 315;
            case "C" : sinViento = true; return 0;
            default: return 0; // Valor por defecto
        }
    }

    private void drawCalmPoint(Graphics2D g2d, int centerX, int centerY) {
        // Dibujar un punto en el centro
        g2d.setColor(Color.BLUE);
        g2d.fillOval(centerX - 5, centerY - 5, 10, 10); // Círculo con radio de 5 píxeles
    }

    public void mostrarGrafico(ArrayList<WeatherData> datos) {

        String direccionViento = datos.get(0).getViento().split(" ")[0]; // Asume que la dirección es la primera palabra
        System.out.println("Dirección del viento: " + direccionViento);

        // Ruta de la imagen de la brújula
        String imagePath = "src/main/java/imagenes/brujula.png"; // Reemplaza con la ruta real

        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen de fondo: " + e.getMessage());
            e.printStackTrace();
        }

        angle = getAngulo(direccionViento);
        // Crear el marco y añadir el panel de la brújula
        JFrame frame = new JFrame("Brújula - Dirección del Viento");
        frame.add(this);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public JPanel crearGrafico(ArrayList<WeatherData> datos) {
        // Crear un nuevo panel de brújula
        BrujulaConFlecha brujula = new BrujulaConFlecha();

        // Obtener la dirección del viento del primer dato
        String direccionViento = datos.get(0).getViento().split(" ")[0]; // Asume que la dirección es la primera palabra

        // Calcular el ángulo basado en la dirección
        brujula.angle = brujula.getAngulo(direccionViento);

        // Ruta de la imagen de la brújula
        String imagePath = "src/main/java/imagenes/brujula.png"; // Asegúrate de usar la ruta correcta

        // Cargar la imagen de fondo (brújula)
        try {
            brujula.backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen de fondo: " + e.getMessage());
            e.printStackTrace();
        }

        // Crear y configurar el panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(brujula, BorderLayout.CENTER);

        return panel;
    }
}




