package ejercicio3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ContadorPalabras {

    public static void main(String[] args) {

        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccionar archivo de texto");
        selector.setFileFilter(new FileNameExtensionFilter("Archivos TXT", "txt"));

        File archivo = null;

        while (archivo == null) {

            int opcion = selector.showOpenDialog(null);

            if (opcion != JFileChooser.APPROVE_OPTION) {
                System.out.println("No se seleccionó ningún archivo.");
                return;
            }

            File seleccionado = selector.getSelectedFile();

            if (seleccionado.exists() && seleccionado.isFile() && seleccionado.canRead()) {
                archivo = seleccionado;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Archivo inválido. Seleccione otro archivo.");
            }
        }

        int lineas = 0;
        int palabras = 0;
        int caracteres = 0;

        Map<String, Integer> frecuencia = new HashMap<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                lineas++;
                caracteres += linea.length();

                StringBuilder palabra = new StringBuilder();

                for (int i = 0; i < linea.length(); i++) {

                    char caracter = linea.charAt(i);

                    if (Character.isLetterOrDigit(caracter)) {
                        palabra.append(Character.toLowerCase(caracter));
                    } else {
                        if (palabra.length() > 0) {
                            String texto = palabra.toString();
                            palabras++;
                            frecuencia.put(texto,
                                    frecuencia.getOrDefault(texto, 0) + 1);
                            palabra.setLength(0);
                        }
                    }
                }

                if (palabra.length() > 0) {
                    String texto = palabra.toString();
                    palabras++;
                    frecuencia.put(texto,
                            frecuencia.getOrDefault(texto, 0) + 1);
                }
            }

            double promedio = lineas > 0 ? (double) palabras / lineas : 0;

            System.out.println("===== CONTADOR DE PALABRAS =====");
            System.out.println("Archivo: " + archivo.getName());
            System.out.println("Total de líneas: " + lineas);
            System.out.println("Total de palabras: " + palabras);
            System.out.println("Total de caracteres: " + caracteres);
            System.out.printf("Promedio de palabras por línea: %.2f%n", promedio);

            System.out.println("\n===== PALABRAS MÁS FRECUENTES =====");

            List<Map.Entry<String, Integer>> lista =
                    new ArrayList<>(frecuencia.entrySet());

            lista.sort((a, b) -> {
                int comparacion = b.getValue().compareTo(a.getValue());
                return comparacion != 0
                        ? comparacion
                        : a.getKey().compareTo(b.getKey());
            });

            for (int i = 0; i < Math.min(10, lista.size()); i++) {
                System.out.println(lista.get(i).getKey() + ": "
                        + lista.get(i).getValue());
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al leer el archivo: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }
}