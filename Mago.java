public abstract class Mago {

    protected String nombre;
    protected String casa;
    protected int nivel;
    protected int vida;
    protected Hechizo[] hechizos;

    public Mago(String nombre, String casa, int nivel, int vida, Hechizo[] hechizos) {
        this.nombre = nombre;
        this.casa = casa;
        this.nivel = nivel;
        this.vida = vida;
        this.hechizos = hechizos;
    }

    public void lanzarHechizo(int index, Mago objetivo) {
        Hechizo hechizo = this.hechizos[index];

        if (hechizo.getTipo().equals("curación")) {
            int cantidadCuracion = hechizo.getCuracionBase() + nivel * 2;
            this.curar(cantidadCuracion);
            System.out.println(this.nombre + " usó " + hechizo.getNombre() + " y se curó " + cantidadCuracion + " puntos de vida.");
        } else if (hechizo.getTipo().equals("ataque") || hechizo.getTipo().equals("ataque_oscuro")) {
            int daño = hechizo.calcularDaño(nivel, casa);
            objetivo.recibirDaño(daño);
            System.out.println(this.nombre + " atacó a " + objetivo.getNombre() + " con " + hechizo.getNombre() + ", causando " + daño + " de daño.");
        } else {
            System.out.println(this.nombre + " lanzó " + hechizo.getNombre() + ", pero no pasó nada.");
        }
    }

    public void curar(int cantidad) {
        this.vida += cantidad;
        if (this.vida > 100) {
            this.vida = 100;
        }
        System.out.println(this.nombre + " se ha curado " + cantidad + " puntos de vida. Vida actual: " + this.vida);
    }

    public void recibirDaño(int daño) {
        vida -= daño;
        if (vida < 0) {
            vida = 0;
        }
    }

    public void subirNivel() {
        nivel++;
        vida += 20;
    }

    public int getVida() {
        return vida;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCasa() {
        return casa;
    }

    public Hechizo[] getHechizos() {
        return hechizos;
    }
}
