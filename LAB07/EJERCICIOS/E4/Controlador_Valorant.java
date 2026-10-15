package S7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Controlador_Valorant {

	private Modelo_Valorant modelo;
	private Vista_Valorant vista;
	private final String ARCHIVO="agentes.txt";

	public Controlador_Valorant(Modelo_Valorant modelo,Vista_Valorant vista) {
		this.modelo=modelo;
		this.vista=vista;
	}

	public void iniciar() {
		vista.mostrarTitulo();

		try {
			leerRegistros();
			vista.mostrarMensaje("\nREGISTROS AL INICIAR:");
			vista.mostrarLista(modelo.getAgentes());
		}catch(IOException e) {
			vista.mostrarMensaje("Error al leer el archivo: "+e.getMessage());
		}

		int opcion;

		do {
			vista.mostrarMenu();
			opcion=vista.pedirOpcion();

			switch(opcion) {
				case 1:
					listarRegistros();
					break;
				case 2:
					agregarRegistro();
					break;
				case 3:
					buscarRegistro();
					break;
				case 4:
					eliminarRegistro();
					break;
				case 5:
					vista.mostrarMensaje("Programa finalizado.");
					break;
				default:
					vista.mostrarMensaje("Opcion invalida.");
			}

		}while(opcion!=5);
	}

	public void leerRegistros() throws IOException {
		File archivo=new File(ARCHIVO);

		modelo.limpiarAgentes();

		if(!archivo.exists()) {
			return;
		}

		BufferedReader br=new BufferedReader(new FileReader(archivo));
		String linea;

		while((linea=br.readLine())!=null) {
			String[] datos=linea.split("\\|");

			if(datos.length==5) {
				try {
					int id=Integer.parseInt(datos[0]);
					String nombre=datos[1];
					String tipo=datos[2];
					double salud=Double.parseDouble(datos[3]);
					String habilidad=datos[4];

					Modelo_Valorant.Agente agente=new Modelo_Valorant.Agente(id,nombre,tipo,salud,habilidad);
					modelo.agregarAgente(agente);

				}catch(IllegalArgumentException e) {
					vista.mostrarMensaje("Registro invalido encontrado.");
				}
			}
		}

		br.close();
	}

	public void listarRegistros() {
		vista.mostrarLista(modelo.getAgentes());
	}

	public void agregarRegistro() {
		try {
			int id=vista.pedirId();

			if(modelo.buscarPorId(id)!=null) {
				vista.mostrarMensaje("Ya existe un agente con ese ID.");
				return;
			}

			String nombre=vista.pedirNombre();
			String tipo=vista.pedirTipoAgente();
			double salud=vista.pedirSalud();
			String habilidad=vista.pedirHabilidad();

			Modelo_Valorant.Agente agente=new Modelo_Valorant.Agente(id,nombre,tipo,salud,habilidad);

			BufferedWriter bw=new BufferedWriter(new FileWriter(ARCHIVO,true));

			bw.write(agente.getId()+"|"+agente.getNombre()+"|"+agente.getTipoAgente()+"|"+
					agente.getSalud()+"|"+agente.getHabilidad());

			bw.newLine();
			bw.close();

			modelo.agregarAgente(agente);

			vista.mostrarMensaje("Agente agregado correctamente.");

		}catch(IllegalArgumentException e) {
			vista.mostrarMensaje("Error en los datos: "+e.getMessage());

		}catch(IOException e) {
			vista.mostrarMensaje("Error al guardar: "+e.getMessage());
		}
	}

	public void buscarRegistro() {
		int id=vista.pedirId();

		Modelo_Valorant.Agente agente=modelo.buscarPorId(id);

		if(agente!=null) {
			vista.mostrarMensaje("\nAGENTE ENCONTRADO:");
			vista.mostrarAgente(agente);
		}else {
			vista.mostrarMensaje("No existe un agente con ese ID.");
		}
	}

	public void eliminarRegistro() {
		int id=vista.pedirId();

		Modelo_Valorant.Agente agente=modelo.buscarPorId(id);

		if(agente==null) {
			vista.mostrarMensaje("No existe un agente con ese ID.");
			return;
		}

		modelo.eliminarAgente(agente);

		try {
			guardarRegistros();
			vista.mostrarMensaje("Agente eliminado correctamente.");

		}catch(IOException e) {
			vista.mostrarMensaje("Error al actualizar el archivo: "+e.getMessage());
		}
	}

	private void guardarRegistros() throws IOException {
		BufferedWriter bw=new BufferedWriter(new FileWriter(ARCHIVO));

		for(Modelo_Valorant.Agente agente:modelo.getAgentes()) {
			bw.write(agente.getId()+"|"+agente.getNombre()+"|"+agente.getTipoAgente()+"|"+
					agente.getSalud()+"|"+agente.getHabilidad());

			bw.newLine();
		}

		bw.close();
	}
}