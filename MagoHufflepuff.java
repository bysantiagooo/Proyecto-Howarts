public class MagoHufflepuff extends Mago {
    public MagoHufflepuff(String nombre, int nivel, int vida, Hechizo[] hechizos) {
        super(nombre, "Hufflepuff", nivel, vida, hechizos);
    }

    @Override
    public void lanzarHechizo(int indice, Mago oponente) {
        Hechizo hechizo = hechizos[indice];
        if (hechizo.getTipo().equals("curación")) {
            int cantidadCuracion = hechizo.getCuracionBase();
            this.curar(cantidadCuracion);
            System.out.println(nombre + " se curó " + cantidadCuracion + " puntos de vida usando " + hechizo.getNombre() + "!");
            return;
        }
        int daño = hechizo.calcularDaño(nivel, casa);
        if (hechizo.getTipo().equals("curación")) {
            daño *= 1.25;
        }
        oponente.recibirDaño(daño);
        System.out.println(nombre + " lanza " + hechizo.getNombre() + " causando " + daño + " de daño!");
    }
}