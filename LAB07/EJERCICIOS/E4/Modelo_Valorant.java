package S7;
import java.util.ArrayList;
public class Modelo_Valorant {
	private ArrayList<Agente> agentes;
	public Modelo_Valorant() {
		agentes=new ArrayList<>();
	}

	public ArrayList<Agente> getAgentes() {
		return agentes;
	}

	public void agregarAgente(Agente agente) {
		agentes.add(agente);
	}

	public void eliminarAgente(Agente agente) {
		agentes.remove(agente);
	}

	public Agente buscarPorId(int id) {
		for(Agente agente:agentes) {
			if(agente.getId()==id) {
				return agente;
			}
		}
		return null;
	}

	public void limpiarAgentes() {
		agentes.clear();
	}

	public static class Agente {
		private int id;
		private String nombre;
		private String tipoAgente;
		private double salud;
		private String habilidad;

		public Agente(int id,String nombre,String tipoAgente,double salud,String habilidad) {
			setId(id);
			setNombre(nombre);
			setTipoAgente(tipoAgente);
			setSalud(salud);
			setHabilidad(habilidad);
		}

		public int getId() {
			return id;
		}

		public String getNombre() {
			return nombre;
		}

		public String getTipoAgente() {
			return tipoAgente;
		}

		public double getSalud() {
			return salud;
		}

		public String getHabilidad() {
			return habilidad;
		}

		public void setId(int id) {
			if(id<=0) {
				throw new IllegalArgumentException("El ID debe ser mayor a 0");
			}
			this.id=id;
		}

		public void setNombre(String nombre) {
			if(nombre==null || nombre.isEmpty()) {
				throw new IllegalArgumentException("El nombre es invalido");
			}
			this.nombre=nombre;
		}

		public void setTipoAgente(String tipoAgente) {
			if(tipoAgente==null || tipoAgente.isEmpty()) {
				throw new IllegalArgumentException("El tipo de agente es invalido");
			}
			this.tipoAgente=tipoAgente;
		}

		public void setSalud(double salud) {
			if(salud<0 || salud>100) {
				throw new IllegalArgumentException("La salud debe estar entre 0 y 100");
			}
			this.salud=salud;
		}

		public void setHabilidad(String habilidad) {
			if(habilidad==null || habilidad.isEmpty()) {
				throw new IllegalArgumentException("La habilidad es invalida");
			}
			this.habilidad=habilidad;
		}

		@Override
		public String toString() {
			return "ID: "+id+" | Nombre: "+nombre+" | Tipo: "+tipoAgente+
					" | Salud: "+salud+" | Habilidad: "+habilidad;
		}
	}
}