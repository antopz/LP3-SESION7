package ACTIVIDAD;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();

        gestor.mostrarPersonajes();

        gestor.añadirPersonajes("Caballero", 4, 2, 4, 2);
        gestor.añadirPersonajes("Guerrero", 2, 4, 2, 4);
        gestor.añadirPersonajes("Arquero", 2, 4, 1, 8);

        gestor.añadirPersonajes("Caballero", 5, 5, 5, 5);

        gestor.añadirPersonajes("Mago", 0, 5, 5, 5);

        gestor.mostrarPersonajes();

        gestor.modificarPersonaje("Guerrero", 10, 10, 10, 10);

        gestor.borrarPersonaje("Arquero");

        gestor.mostrarPersonajes();
    }
}