package ACTIVIDAD;

public class Main {

	public static void main(String[] args) {

		Gestor gestor=new Gestor();

		System.out.println("PERSONAJES AL INICIAR");
		gestor.mostrarPersonajes();

		gestor.añadirPersonajes("Caballero", 4, 2, 4, 2);
		gestor.añadirPersonajes("Guerrero", 2, 4, 2, 4);
		gestor.añadirPersonajes("Arquero", 2, 4, 1, 8);
		gestor.añadirPersonajes("Mago", 0, 5, 5, 5);

		System.out.println("\nORDENADOS POR ATAQUE");
		gestor.filtrarPersonajesPorAtributo("ataque");

		System.out.println("\nACTUALIZAR UN SOLO ATRIBUTO");
		gestor.actualizarAtributo("Guerrero", "vida", 12);

		System.out.println("\nSUBIR DE NIVEL");
		gestor.subirNivel("Guerrero");

		System.out.println("\nMODIFICAR PERSONAJE");
		gestor.modificarPersonaje("Caballero", 6, 5, 6, 3);

		System.out.println("\nIMPORTAR PERSONAJES");
		gestor.importarPersonajes("personajes_importar.txt");

		System.out.println("\nESTADISTICAS");
		gestor.mostrarEstadisticas();

		System.out.println("\nELIMINAR PERSONAJE");
		gestor.borrarPersonaje("Arquero");

		System.out.println("\nLISTA FINAL");
		gestor.mostrarPersonajes();
	}
}
