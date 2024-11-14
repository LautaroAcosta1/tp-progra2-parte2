package implementacion;

public class Cliente {
    int dni;
    String nombre;
    String telefono;

    public Cliente(int dni, String nombre, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre + " (DNI: " + dni + ")";
    }
}

