import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¡Bienvenido a Hogwarts!");
        System.out.print("Ingresa el nombre de tu mago: ");
        String nombreMago = scanner.nextLine();

        System.out.println("Elige una casa:");
        System.out.println("1. Gryffindor");
        System.out.println("2. Slytherin");
        System.out.println("3. Ravenclaw");
        System.out.println("4. Hufflepuff");
        int casa = scanner.nextInt();
        scanner.nextLine();

        Mago jugador = null;
        switch (casa) {
            case 1:
                jugador = new MagoGryffindor(nombreMago, 1, 100, seleccionarHechizos(scanner));
                break;
            case 2:
                jugador = new MagoSlytherin(nombreMago, 1, 100, seleccionarHechizos(scanner));
                break;
            case 3:
                jugador = new MagoRavenclaw(nombreMago, 1, 100, seleccionarHechizos(scanner));
                break;
            case 4:
                jugador = new MagoHufflepuff(nombreMago, 1, 100, seleccionarHechizos(scanner));
                break;
        }

        Mago oponente = new MagoSlytherin("Draco Malfoy", 1, 100, seleccionarHechizosAleatorios());
        System.out.println(" Tu oponente es " + oponente.getNombre() + " (" + oponente.getCasa() + ")");
        System.out.println("Draco ha seleccionado sus hechizos...");

        System.out.println("¡Que comience el duelo!");
        while (jugador.getVida() > 0 && oponente.getVida() > 0) {
            turnoJugador(scanner, jugador, oponente);
            if (oponente.getVida() > 0) {
                turnoOponente(jugador, oponente);
            }
        }

        if (jugador.getVida() > 0) {
            System.out.println(oponente.getNombre() + " ha sido derrotado!");
            jugador.subirNivel();
            System.out.println(jugador.getNombre() + " sube a nivel " + jugador.nivel + ".");
        } else {
            System.out.println(jugador.getNombre() + " ha sido derrotado!");
        }

        System.out.println("El duelo ha terminado.");
    }

    private static Hechizo[] seleccionarHechizos(Scanner scanner) {
        Hechizo[] hechizosSeleccionados = new Hechizo[3];
        System.out.println("Elige 3 hechizos de la lista:");
        for (int i = 0; i < BibliotecaHechizos.TodosLosHechizos.length; i++) {
            System.out.println(i + ": " + BibliotecaHechizos.TodosLosHechizos[i].getNombre());
        }
        for (int i = 0; i < 3; i++) {
            System.out.print("Selecciona el hechizo " + (i + 1) + ": ");
            int seleccion = scanner.nextInt();
            scanner.nextLine();
            hechizosSeleccionados[i] = BibliotecaHechizos.TodosLosHechizos[seleccion];
        }
        return hechizosSeleccionados;
    }


    private static Hechizo[] seleccionarHechizosAleatorios() {
        Hechizo[] hechizosSeleccionados = new Hechizo[3];
        Random random = new Random();
        Hechizo[] todosLosHechizos = BibliotecaHechizos.TodosLosHechizos;
        for (int i = 0; i < 3; i++) {
            int seleccion = random.nextInt(todosLosHechizos.length);
            hechizosSeleccionados[i] = todosLosHechizos[seleccion];
        }
        return hechizosSeleccionados;
    }


    private static void turnoJugador(Scanner scanner, Mago jugador, Mago oponente) {
        System.out.println("Turno de " + jugador.getNombre() + ":");
        Hechizo[] hechizos = jugador.getHechizos();
        for (int i = 0; i < hechizos.length; i++) {
            System.out.println(i + ". " + hechizos[i].getNombre());
        }
        System.out.print("Selecciona un hechizo: ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();
        jugador.lanzarHechizo(seleccion, oponente);
        mostrarEstado(jugador, oponente);
    }

    private static void turnoOponente(Mago jugador, Mago oponente) {
        System.out.println("Turno de " + oponente.getNombre() + ":");
        Random random = new Random();
        int seleccion = random.nextInt(oponente.getHechizos().length);
        oponente.lanzarHechizo(seleccion, jugador);
        mostrarEstado(jugador, oponente);
    }

    private static void mostrarEstado(Mago jugador, Mago oponente) {
        System.out.println("Estado actual:");
        System.out.println("- " + jugador.getNombre() + ": " + jugador.getVida() + " de vida restante.");
        System.out.println("- " + oponente.getNombre() + ": " + oponente.getVida() + " de vida restante.");
    }
}