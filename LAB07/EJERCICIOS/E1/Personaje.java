package ACTIVIDAD;
import java.io.Serializable;
public class Personaje implements Serializable{
	private static final long serialVersionUID=1L;
	private String nombre;
	private int vida;
	private int ataque;
	private int defensa;
	private int alcance;
	
	public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
		if (vida<=0 || ataque<=0 || defensa<=0 || alcance<=0) {
			throw new IllegalArgumentException("Error en ingreso de valores");
		}
		this.nombre=nombre;
		this.vida=vida;
		this.ataque=ataque;
		this.defensa=defensa;
		this.alcance=alcance;
	}
	
	public int getAlcance() {
		return alcance;
	}
	public void setAlcance(int alcance) {
		this.alcance = alcance;
	}
	public int getDefensa() {
		return defensa;
	}
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
	public int getAtaque() {
		return ataque;
	}
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		String nR=String.format("%-12s", nombre);		//AYUDA A QUE TENGA EN CUENTA 12 CARACETRES PARA EL NOMBRE
	    return nR + "\t" + vida + "\t" + ataque + "\t" + defensa + "\t" + alcance;
	    //\t sirve como tabulador da un espacio considerable	//falla con cierta cantidad de caracteres
	}
}
