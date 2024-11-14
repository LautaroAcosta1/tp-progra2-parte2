package implementacion;

public class Aeropuerto {
    String nombre;
    String pais;
    String provincia;
    String direccion;

    public Aeropuerto(String nombre, String pais, String provincia, String direccion) {
        this.nombre = nombre;
        this.pais = pais;
        this.provincia = provincia;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return nombre + " - " + direccion + ", " + provincia + ", " + pais;
    }
}

