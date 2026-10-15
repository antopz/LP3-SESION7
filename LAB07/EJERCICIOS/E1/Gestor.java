package ACTIVIDAD;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;			//persistencia de objetos
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Gestor {
	private List<Personaje> personajes;
	private final String ARCHIVO="personajes.dat";
	
	public Gestor() {
		this.personajes=new ArrayList<>();
		cargarArchivo();
	}

	public void añadirPersonajes(String nombre, int vida, int ataque, int defensa, int alcance) {
		if (buscarPersonaje(nombre)!=null) {
			System.out.println("El personaje ya existe");
			return;
		}
		
		try {
			Personaje nPers=new Personaje(nombre, vida, ataque,defensa, alcance);
			personajes.add(nPers);
			guardarEnArchivo();
			System.out.println("Personaje añadido");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	public void mostrarPersonajes() {
		if (personajes.isEmpty()) {
			System.out.println("No hay personajes registrados");
			return;
		}
		
		System.out.println("Nombre\t\tVida\tAtaque\tDefensa\tAlcance");
		
		
		for (Personaje p: personajes) {
			System.out.println(p.toString());
		}
	}
	
	public Personaje buscarPersonaje(String nombre) {
		for (Personaje p: personajes) {
			if (p.getNombre().equalsIgnoreCase(nombre)) {
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
		
		p.setVida(nVida);
		p.setAtaque(nAtaque);
		p.setDefensa(nDefensa);
		p.setAlcance(nAlcance);
		guardarEnArchivo();
		System.out.println("Personaje modificado");
	}
	
	public void borrarPersonaje(String nombre) {
		Personaje p = buscarPersonaje(nombre);
		if (p==null) {
			System.out.println("Personaje no existe");
			return;
		}
		personajes.remove(p);
		guardarEnArchivo();
		System.out.println("Personaje eliminado");
	}
	
	private void cargarArchivo() {		
		File archivo=new File(ARCHIVO);
		if (!archivo.exists()) {
			return;
		}
		
		try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(archivo))){
			int cantidad=ois.readInt(); 
			
			for (int i=0; i<cantidad;i++) {
				Personaje p=(Personaje) ois.readObject();
				personajes.add(p);
			}
		} catch(IOException | ClassNotFoundException e) {
			System.out.println("Error"+e.getMessage());
		}
	}
	
	private void guardarEnArchivo() {
		try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(ARCHIVO))){
			oos.writeInt(personajes.size());
			
			for(Personaje p:personajes) {
				oos.writeObject(p);
			}
		} catch (IOException e) {
			System.out.println("Error"+e.getMessage());
		}
	}
}