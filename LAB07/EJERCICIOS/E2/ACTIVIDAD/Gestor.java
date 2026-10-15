package ACTIVIDAD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gestor {

	private List<Personaje> personajes;
	private final String ARCHIVO="personajes.dat";

	public Gestor() {
		personajes=new ArrayList<>();
		cargarArchivo();

		if(personajes.isEmpty()) {
			cargarPersonajesAleatorios();
		}
	}

	public void añadirPersonajes(String nombre, int vida, int ataque, int defensa, int alcance) {

		if(buscarPersonaje(nombre)!=null) {
			System.out.println("El personaje ya existe");
			return;
		}

		try {
			Personaje nPers=new Personaje(nombre, vida, ataque, defensa, alcance);
			personajes.add(nPers);
			guardarEnArchivo();
			System.out.println("Personaje añadido");

		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void mostrarPersonajes() {

		if(personajes.isEmpty()) {
			System.out.println("No hay personajes registrados");
			return;
		}

		System.out.println("Nombre\t\tVida\tAtaque\tDefensa\tAlcance\tNivel");

		for(Personaje p:personajes) {
			System.out.println(p);
		}
	}

	public Personaje buscarPersonaje(String nombre) {

		for(Personaje p:personajes) {
			if(p.getNombre().equalsIgnoreCase(nombre)) {
				return p;
			}
		}

		return null;
	}

	public void modificarPersonaje(String nombre, int nVida, int nAtaque, int nDefensa, int nAlcance) {

		Personaje p=buscarPersonaje(nombre);

		if(p==null) {
			System.out.println("El personaje no existe");
			return;
		}

		if(nVida<=0 || nAtaque<=0 || nDefensa<=0 || nAlcance<=0) {
			System.out.println("Error en ingreso de valores");
			return;
		}

		p.setVida(nVida);
		p.setAtaque(nAtaque);
		p.setDefensa(nDefensa);
		p.setAlcance(nAlcance);

		guardarEnArchivo();

		System.out.println("Personaje modificado");
	}

	public void borrarPersonaje(String nombre) {

		Personaje p=buscarPersonaje(nombre);

		if(p==null) {
			System.out.println("Personaje no existe");
			return;
		}

		personajes.remove(p);
		guardarEnArchivo();

		System.out.println("Personaje eliminado");
	}

	public void filtrarPersonajesPorAtributo(String atributo) {

		if(personajes.isEmpty()) {
			System.out.println("No hay personajes registrados");
			return;
		}

		List<Personaje> ordenados=new ArrayList<>(personajes);

		switch(atributo.toLowerCase()) {

			case "vida":
				ordenados.sort((p1,p2)->Integer.compare(p2.getVida(), p1.getVida()));
				break;

			case "ataque":
				ordenados.sort((p1,p2)->Integer.compare(p2.getAtaque(), p1.getAtaque()));
				break;

			case "defensa":
				ordenados.sort((p1,p2)->Integer.compare(p2.getDefensa(), p1.getDefensa()));
				break;

			case "alcance":
				ordenados.sort((p1,p2)->Integer.compare(p2.getAlcance(), p1.getAlcance()));
				break;

			case "nivel":
				ordenados.sort((p1,p2)->Integer.compare(p2.getNivel(), p1.getNivel()));
				break;

			default:
				System.out.println("Atributo no valido");
				return;
		}

		System.out.println("Personajes ordenados por "+atributo+" de mayor a menor:");
		System.out.println("Nombre\t\tVida\tAtaque\tDefensa\tAlcance\tNivel");

		for(Personaje p:ordenados) {
			System.out.println(p);
		}
	}

	public void cargarPersonajesAleatorios() {

		Random random=new Random();
		String nombres[]={"Ninja", "Paladin", "Hechicero"};

		int agregados=0;

		for(String nombre:nombres) {

			if(buscarPersonaje(nombre)==null) {

				int vida=random.nextInt(10)+1;
				int ataque=random.nextInt(10)+1;
				int defensa=random.nextInt(10)+1;
				int alcance=random.nextInt(10)+1;

				personajes.add(new Personaje(nombre, vida, ataque, defensa, alcance));
				agregados++;
			}
		}

		if(agregados>0) {
			guardarEnArchivo();
			System.out.println("Personajes aleatorios cargados");
		}
	}

	public void actualizarAtributo(String nombre, String atributo, int valor) {

		Personaje p=buscarPersonaje(nombre);

		if(p==null) {
			System.out.println("El personaje no existe");
			return;
		}

		try {

			switch(atributo.toLowerCase()) {

				case "vida":
					p.setVida(valor);
					break;

				case "ataque":
					p.setAtaque(valor);
					break;

				case "defensa":
					p.setDefensa(valor);
					break;

				case "alcance":
					p.setAlcance(valor);
					break;

				default:
					System.out.println("Atributo no valido");
					return;
			}

			guardarEnArchivo();

			System.out.println("Atributo actualizado");

		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void mostrarEstadisticas() {

		if(personajes.isEmpty()) {
			System.out.println("No hay personajes registrados");
			return;
		}

		int totalVida=0;
		int totalAtaque=0;
		int totalDefensa=0;
		int totalAlcance=0;

		for(Personaje p:personajes) {
			totalVida+=p.getVida();
			totalAtaque+=p.getAtaque();
			totalDefensa+=p.getDefensa();
			totalAlcance+=p.getAlcance();
		}

		int cantidad=personajes.size();

		System.out.println("Total de personajes: "+cantidad);
		System.out.printf("Promedio de vida: %.2f%n", (double)totalVida/cantidad);
		System.out.printf("Promedio de ataque: %.2f%n", (double)totalAtaque/cantidad);
		System.out.printf("Promedio de defensa: %.2f%n", (double)totalDefensa/cantidad);
		System.out.printf("Promedio de alcance: %.2f%n", (double)totalAlcance/cantidad);
	}

	public void importarPersonajes(String nombreArchivo) {

		File archivo=new File(nombreArchivo);

		if(!archivo.exists()) {
			System.out.println("El archivo de importacion no existe");
			return;
		}

		int importados=0;

		try(BufferedReader br=new BufferedReader(new FileReader(archivo))) {

			String linea;

			while((linea=br.readLine())!=null) {

				if(linea.trim().isEmpty()) {
					continue;
				}

				String datos[]=linea.split(",");

				if(datos.length!=5) {
					System.out.println("Linea ignorada: "+linea);
					continue;
				}

				try {

					String nombre=datos[0].trim();
					int vida=Integer.parseInt(datos[1].trim());
					int ataque=Integer.parseInt(datos[2].trim());
					int defensa=Integer.parseInt(datos[3].trim());
					int alcance=Integer.parseInt(datos[4].trim());

					if(buscarPersonaje(nombre)!=null) {
						System.out.println(nombre+" ya existe y no se importo");
						continue;
					}

					Personaje p=new Personaje(nombre, vida, ataque, defensa, alcance);
					personajes.add(p);
					importados++;

				} catch(IllegalArgumentException e) {
					System.out.println("Linea invalida: "+linea);
				}
			}

			if(importados>0) {
				guardarEnArchivo();
			}

			System.out.println("Personajes importados: "+importados);

		} catch(IOException e) {
			System.out.println("Error "+e.getMessage());
		}
	}

	public void subirNivel(String nombre) {

		Personaje p=buscarPersonaje(nombre);

		if(p==null) {
			System.out.println("El personaje no existe");
			return;
		}

		p.subirNivel();
		guardarEnArchivo();

		System.out.println(nombre+" subio al nivel "+p.getNivel());
	}

	private void cargarArchivo() {

		File archivo=new File(ARCHIVO);

		if(!archivo.exists()) {
			return;
		}

		try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(archivo))) {

			int cantidad=ois.readInt();

			for(int i=0;i<cantidad;i++) {
				Personaje p=(Personaje)ois.readObject();
				personajes.add(p);
			}

		} catch(IOException | ClassNotFoundException e) {
			System.out.println("Error "+e.getMessage());
		}
	}

	private void guardarEnArchivo() {

		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {

			oos.writeInt(personajes.size());

			for(Personaje p:personajes) {
				oos.writeObject(p);
			}

		} catch(IOException e) {
			System.out.println("Error "+e.getMessage());
		}
	}
}
