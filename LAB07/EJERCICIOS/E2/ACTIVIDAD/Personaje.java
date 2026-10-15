package ACTIVIDAD;

import java.io.Serializable;

public class Personaje implements Serializable {

	private static final long serialVersionUID=1L;

	private String nombre;
	private int vida;
	private int ataque;
	private int defensa;
	private int alcance;
	private int nivel;

	public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {

		if(nombre==null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede estar vacio");
		}

		validarValor(vida);
		validarValor(ataque);
		validarValor(defensa);
		validarValor(alcance);

		this.nombre=nombre;
		this.vida=vida;
		this.ataque=ataque;
		this.defensa=defensa;
		this.alcance=alcance;
		this.nivel=1;
	}

	private void validarValor(int valor) {
		if(valor<=0) {
			throw new IllegalArgumentException("Error en ingreso de valores");
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(nombre==null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede estar vacio");
		}
		this.nombre=nombre;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		validarValor(vida);
		this.vida=vida;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		validarValor(ataque);
		this.ataque=ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		validarValor(defensa);
		this.defensa=defensa;
	}

	public int getAlcance() {
		return alcance;
	}

	public void setAlcance(int alcance) {
		validarValor(alcance);
		this.alcance=alcance;
	}

	public int getNivel() {
		if(nivel<=0) {
			return 1;
		}
		return nivel;
	}

	public void subirNivel() {
		if(nivel<=0) {
			nivel=1;
		}
		nivel++;
		vida++;
		ataque++;
		defensa++;
		alcance++;
	}

	@Override
	public String toString() {
		String nR=String.format("%-12s", nombre);
		return nR+"\t"+vida+"\t"+ataque+"\t"+defensa+"\t"+alcance+"\t"+getNivel();
	}
}
