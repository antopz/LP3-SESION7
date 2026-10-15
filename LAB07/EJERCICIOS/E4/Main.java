package S7;
public class Main {
	public static void main(String[] args) {
		Modelo_Valorant modelo=new Modelo_Valorant();
		Vista_Valorant vista=new Vista_Valorant();
		Controlador_Valorant controlador=new Controlador_Valorant(modelo,vista);
		controlador.iniciar();
	}
}