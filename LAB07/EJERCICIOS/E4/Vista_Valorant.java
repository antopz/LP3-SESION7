package S7;

import java.util.ArrayList;
import java.util.Scanner;

public class Vista_Valorant {

	private Scanner sc;

	public Vista_Valorant() {
		sc=new Scanner(System.in);
	}

	public void mostrarTitulo() {
		System.out.println("============================");
		System.out.println("    SISTEMA DE VALORANT");
		System.out.println("============================");
	}

	public void mostrarMenu() {
		System.out.println("\n1. Listar agentes");
		System.out.println("2. Agregar agente");
		System.out.println("3. Buscar agente por ID");
		System.out.println("4. Eliminar agente por ID");
		System.out.println("5. Salir");
	}

	public int pedirOpcion() {
		System.out.print("Ingrese una opcion: ");
		return leerEntero();
	}

	public int pedirId() {
		System.out.print("Ingrese el ID: ");
		return leerEntero();
	}

	public String pedirNombre() {
		System.out.print("Ingrese el nombre: ");
		return sc.nextLine();
	}

	public String pedirTipoAgente() {
		System.out.print("Ingrese el tipo de agente: ");
		return sc.nextLine();
	}

	public double pedirSalud() {
		System.out.print("Ingrese la salud: ");
		return leerDouble();
	}

	public String pedirHabilidad() {
		System.out.print("Ingrese la habilidad: ");
		return sc.nextLine();
	}

	public void mostrarAgente(Modelo_Valorant.Agente agente) {
		System.out.println(agente);
	}

	public void mostrarLista(ArrayList<Modelo_Valorant.Agente> agentes) {
		if(agentes.isEmpty()) {
			System.out.println("No hay agentes registrados.");
			return;
		}

		for(Modelo_Valorant.Agente agente:agentes) {
			System.out.println(agente);
		}
	}

	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}

	private int leerEntero() {
		while(true) {
			try {
				return Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.print("Ingrese un numero entero: ");
			}
		}
	}

	private double leerDouble() {
		while(true) {
			try {
				return Double.parseDouble(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.print("Ingrese un numero valido: ");
			}
		}
	}
}