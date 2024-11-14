package implementacion;

import java.util.HashMap;
import java.util.Map;

public class Vuelo {
    String codigo;
    String origen;
    String destino;
    String fecha;
    int tripulantes;
    Map<Integer, Boolean> asientos; // ver si el asiento está ocupado
    Map<Integer, Double> preciosAsientos; // almacena el precio de cada asiento

    public Vuelo(String codigo, String origen, String destino, String fecha, int tripulantes, int[] cantAsientos) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.tripulantes = tripulantes;
        this.asientos = new HashMap<>();

        // inicializa los asientos como libres
        for (int asiento = 0; asiento < cantAsientos.length; asiento++) {
            this.asientos.put(asiento + 1, false);
        }
    }

    public int venderPasaje(int dni, int nroAsiento, boolean aOcupar) {
        if (asientos.get(nroAsiento) != null && !asientos.get(nroAsiento)) {
            asientos.put(nroAsiento, aOcupar);
            return nroAsiento;
        } else {
            System.out.println("El asiento no está disponible.");
            return -1;
        }
    }

    public Map<Integer, String> obtenerAsientosDisponibles() {
        Map<Integer, String> asientosDisponibles = new HashMap<>();

        for (Map.Entry<Integer, Boolean> entry : asientos.entrySet()) {
            asientosDisponibles.put(entry.getKey(), entry.getValue() ? "Ocupado" : "Libre");
        }

        return asientosDisponibles;
    }
    
    public double obtenerPrecioTotal() {
        double total = 0.0;
        for (Map.Entry<Integer, Boolean> entry : asientos.entrySet()) {
            if (entry.getValue()) { // si el asiento está ocupado
                total += preciosAsientos.get(entry.getKey()); // suma el precio de ese asiento
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "Vuelo " + codigo + " de " + origen + " a " + destino + " el " + fecha;
    }

}
