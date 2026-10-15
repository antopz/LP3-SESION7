package S7;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class act1 {
	public static void main(String[] args) throws IOException {
		Scanner sc= new Scanner(System.in);
		System.out.println("ESCRIBA EL NOMBRE DE ARCHIVO");
		Path ruta= Paths.get(sc.nextLine());
		if(Files.exists(ruta)) {
			System.out.printf("%n%s existe%n", ruta.getFileName());
			System.out.printf("%s un directorio%n", Files.isDirectory(ruta)? "Es ":"No es");
			System.out.printf("%s una ruta absoluta%n", ruta.isAbsolute()? "Es ": "No es");
			System.out.printf("Fecha de ultima modificacion: %s&n", Files.getLastModifiedTime(ruta));
			System.out.printf("Tamanio: %s%n", ruta.toAbsolutePath());
			System.out.printf("Ruta:  %s%n",ruta.toAbsolutePath());
			if (Files.isDirectory(ruta )) {
				System.out.printf("%n Contenido del directorio:%n");
				DirectoryStream<Path> flujoDirectorio=Files.newDirectoryStream(ruta);
				for(Path p:flujoDirectorio) {
					System.out.println(p);
				}
			}
		}else {
			System.out.printf("%s no existe%n",ruta);
		}
	
	}
	
}
