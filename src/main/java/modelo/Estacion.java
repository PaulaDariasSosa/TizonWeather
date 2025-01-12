package modelo;

import estados.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Estacion implements Observer {
    private ArrayList<WeatherData> datos_;
    private static String diaActual_;
    private int idEstacion_;
    private String nombre_;

    public Estacion(int idEstacion, String nombre) {
        datos_ = new ArrayList<WeatherData>();
        diaActual_ = "";
        idEstacion_ = idEstacion;
        nombre_ = nombre;

        update();
    }

    // Metodo para obtener el XML desde una URL
    public static String getXmlFromUrl(String urlString) throws IOException {
        StringBuilder xmlContent = new StringBuilder();

        // Crear un objeto URL y abrir una conexión
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Establecer el tiempo de espera
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        // Leer la respuesta de la conexión (el contenido XML)
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            xmlContent.append(inputLine);
        }
        in.close();

        return xmlContent.toString();
    }

    // Método para parsear el XML
    public void parseXml(String xmlContent, WeatherData weatherData) throws Exception {
        // Crear el objeto DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Convertir el String en un InputStream para ser procesado por el XML parser
        InputStream inputStream = new ByteArrayInputStream(xmlContent.getBytes("UTF-8"));

        // Parsear el contenido XML en un objeto Document
        Document doc = builder.parse(inputStream);

        // Normalizar el documento
        doc.getDocumentElement().normalize();

        // Obtener la raíz del documento (elemento <root>)
        Element rootElement = doc.getDocumentElement();

        // Obtener el nodo <prediccion>
        NodeList prediccionList = doc.getElementsByTagName("dia");

        // Recorrer todos los nodos <dia>
        for (int i = 0; i < prediccionList.getLength(); i++) {
            Node diaNode = prediccionList.item(i);
            if (diaNode.getNodeType() == Node.ELEMENT_NODE) {
                Element diaElement = (Element) diaNode;

                // Obtener los atributos de <dia>, como la fecha
                String fecha = diaElement.getAttribute("fecha");
                if (diaActual_.equals("")) {
                    diaActual_ = fecha;
                }
                // Extraer información de temperatura
                NodeList temperaturaList = diaElement.getElementsByTagName("temperatura");
                String temperaturaMaxima = "";
                String temperaturaMinima = "";
                if (temperaturaList.getLength() > 0 && temperaturaList.item(0).getNodeType() == Node.ELEMENT_NODE) {
                    Element temperaturaElement = (Element) temperaturaList.item(0);
                    temperaturaMaxima = temperaturaElement.getElementsByTagName("maxima").item(0).getTextContent();
                    temperaturaMinima = temperaturaElement.getElementsByTagName("minima").item(0).getTextContent();
                }

                // Extraer información de estado del cielo
                NodeList estadoCieloList = diaElement.getElementsByTagName("estado_cielo");
                String estadoCielo = "";
                for (int j = 0; j < estadoCieloList.getLength(); j++) {
                    Node estadoCieloNode = estadoCieloList.item(j);
                    if (estadoCieloNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element estadoCieloElement = (Element) estadoCieloNode;
                        estadoCielo = estadoCieloElement.getAttribute("descripcion");
                    }
                }

                // Extraer información de viento
                String viento = "";
                NodeList vientoList = diaElement.getElementsByTagName("viento");
                for (int j = 0; j < vientoList.getLength(); j++) {
                    Node vientoNode = vientoList.item(j);
                    if (vientoNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element vientoElement = (Element) vientoNode;
                        String direccion = "";
                        String velocidad = "";
                        NodeList direccionList = vientoElement.getElementsByTagName("direccion");
                        NodeList velocidadList = vientoElement.getElementsByTagName("velocidad");
                        if (direccionList.getLength() > 0 && direccionList.item(0).getNodeType() == Node.ELEMENT_NODE) {
                            direccion = direccionList.item(0).getTextContent();
                        }
                        if (velocidadList.getLength() > 0 && velocidadList.item(0).getNodeType() == Node.ELEMENT_NODE) {
                            velocidad = velocidadList.item(0).getTextContent();
                        }
                        viento = direccion + " " + velocidad + " km/h";
                    }
                }

                if (estadoCielo.equals("Despejado")) {
                    weatherData.setEstado(new Despejado());
                } else if (estadoCielo.contains("nuboso") || estadoCielo.contains("Nuboso")) {
                    weatherData.setEstado(new Nuboso());
                } else if (estadoCielo.contains("niebla") || estadoCielo.contains("Niebla")) {
                    weatherData.setEstado(new Niebla());
                } else if (estadoCielo.contains("bruma") || estadoCielo.contains("Bruma")) {
                    weatherData.setEstado(new Bruma());
                } else if (estadoCielo.contains("Cubierto") || estadoCielo.contains("cubierto")) {
                    weatherData.setEstado(new Cubierto());
                } else if (estadoCielo.contains("Lluvia") || estadoCielo.contains("lluvia")) {
                    weatherData.setEstado(new Lluvia());
                } else if (estadoCielo.contains("Tormenta") || estadoCielo.contains("tormenta")) {
                    weatherData.setEstado(new Tormenta());
                } else if (estadoCielo.contains("Nieve") || estadoCielo.contains("nieve")) {
                    weatherData.setEstado(new Nieve());
                } else if (estadoCielo.contains("Ceniza") || estadoCielo.contains("ceniza")) {
                    weatherData.setEstado(new Ceniza());
                } else if (estadoCielo.contains("Chubascos") || estadoCielo.contains("chubascos")) {
                    weatherData.setEstado(new Chubascos());
                }
                weatherData.setWeatherData(fecha, temperaturaMaxima, temperaturaMinima, estadoCielo, viento);
                datos_.add(new WeatherData(weatherData));
            }
        }
    }

    //debe tener un metodo para actualizar los datos
    public void update () {
        if (!datos_.isEmpty()) {
            datos_.clear();
            // System.out.println("Datos actualizandose");
        }
        // URL de AEMET donde está el archivo XML
        String url = "https://www.aemet.es/xml/municipios/localidad_" + idEstacion_ + ".xml";

        try {
            try {
                // Crear el objeto modelo.WeatherData y vincularlo con el Dashboard
                WeatherData weatherData = new WeatherData();

                // Obtener el XML desde la URL
                String xmlContent = getXmlFromUrl(url);

                // Parsear el XML y actualizar los datos en el modelo.WeatherData
                parseXml(xmlContent, weatherData);
                //datos_.add(weatherData);
            } catch (Exception e) {
                System.err.println("Error al actualizar el clima: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // metodo para imprimir
    public void print() {
        System.out.println("Datos de la estación meteorológica:");
        System.out.println("  ID de la estación: " + idEstacion_);
        System.out.println("  Día actual: " + diaActual_);
        System.out.println("  Nombre de la estación: " + nombre_);
        System.out.println();
        for (WeatherData data : datos_) {
            data.print();
        }
    }

    public int getIdEstacion() {
        return idEstacion_;
    }

    public ArrayList<WeatherData> getDatos() {
        return datos_;
    }

    public String getDiaActual() {
        return diaActual_;
    }

    public String getNombre() {
        return nombre_;
    }
}
