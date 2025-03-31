public class Hechizo {
    private String nombre;
    private int dañoBase;
    private String tipo;
    private int curacionBase;

    public Hechizo(String nombre, int dañoBase, int curacionBase, String tipo) {
        this.nombre = nombre;
        this.dañoBase = dañoBase;
        this.tipo = tipo;
        this.curacionBase = curacionBase;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getDañoBase() {
        return dañoBase;
    }
    public void setDañoBase(int dañoBase) {
        this.dañoBase = dañoBase;
    }
    public int getCuracionBase() {
        return curacionBase;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int calcularDaño(int nivel, String casa) {
        if (!tipo.equals("ataque") && !tipo.equals("ataque_oscuro")) {
            return 0;
        }
        int daño = dañoBase + nivel * 2;
        switch (casa) {
            case "Gryffindor":
                if (tipo.equals("ataque")) {
                    daño *= 1.5;
                }
                break;
            case "Slytherin":
                if (nombre.equals("ataque")) {
                    daño *= 1.3;
                }
                break;
            case "Ravenclaw":
                if (tipo.equals("defensa")) {
                    daño *= 1.2;
                }
                break;
            case "Hufflepuff":

                break;
        }
        return daño;
    }
}
