package implementacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Aerolinea implements IAerolinea {
    private String nombre;
    private String CUIT;
    private List<Cliente> clientes;
    private List<Aeropuerto> aeropuertos;
    private Map<String, Vuelo> vuelos;

    public Aerolinea(String nombre, String CUIT) {
        this.nombre = nombre;
        this.CUIT = CUIT;
        this.clientes = new ArrayList<>();
        this.aeropuertos = new ArrayList<>();
        this.vuelos = new HashMap<>();
    }

    @Override
    public void registrarCliente(int dni, String nombre, String telefono) {
        for (Cliente cliente : clientes) {
            if (cliente.dni == dni) {
                System.out.println("El cliente ya se encuentra registrado.");
                return;
            }
        }
        clientes.add(new Cliente(dni, nombre, telefono));
    }

    @Override
    public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion) {
        for (Aeropuerto aeropuerto : aeropuertos) {
            if (aeropuerto.nombre.equals(nombre)) {
                System.out.println("El aeropuerto ya se encuentra registrado.");
                return;
            }
        }
        aeropuertos.add(new Aeropuerto(nombre, pais, provincia, direccion));
    }

    @Override
    public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes,
                                                double valorRefrigerio, double[] precios, int[] cantAsientos) {
        String codigoVuelo = "VN-" + origen + "-" + destino + "-" + fecha;

        if (vuelos.containsKey(codigoVuelo)) {
            System.out.println("El vuelo ya está registrado.");
            return codigoVuelo;
        }

        Vuelo vuelo = new Vuelo(codigoVuelo, origen, destino, fecha, tripulantes, cantAsientos);
        vuelos.put(codigoVuelo, vuelo);

        return codigoVuelo;
    }

    @Override
    public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes,
                                                     double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {
        String codigoVuelo = "VI-" + origen + "-" + destino + "-" + fecha;

        if (vuelos.containsKey(codigoVuelo)) {
            System.out.println("El vuelo ya está registrado.");
            return codigoVuelo;
        }

        Vuelo vuelo = new Vuelo(codigoVuelo, origen, destino, fecha, tripulantes, cantAsientos);
        vuelos.put(codigoVuelo, vuelo);

        return codigoVuelo;
    }

    @Override
    public String VenderVueloPrivado(String origen, String destino, String fecha, int tripulantes, double precio,
                                     int dniComprador, int[] acompaniantes) {
        String codigoVuelo = "VP-" + origen + "-" + destino + "-" + fecha;
        Vuelo vuelo = new Vuelo(codigoVuelo, origen, destino, fecha, tripulantes, new int[40]); // Ejemplo para 40 asientos

        vuelos.put(codigoVuelo, vuelo);
        return codigoVuelo;
    }

    @Override
    public Map<Integer, String> asientosDisponibles(String codVuelo) {
        Vuelo vuelo = vuelos.get(codVuelo);

        if (vuelo == null) {
            throw new RuntimeException("El vuelo no existe.");
        }

        return vuelo.obtenerAsientosDisponibles();
    }

    @Override
    public int venderPasaje(int dni, String codVuelo, int nroAsiento, boolean aOcupar) {
        Vuelo vuelo = vuelos.get(codVuelo);

        if (vuelo == null) {
            System.out.println("El vuelo no existe.");
            return -1;
        }

        return vuelo.venderPasaje(dni, nroAsiento, aOcupar);
    }

    @Override
    public List<String> consultarVuelosSimilares(String origen, String destino, String fecha) {
        List<String> vuelosSimilares = new ArrayList<>();

        for (Vuelo vuelo : vuelos.values()) {
            if (vuelo.origen.equals(origen) && vuelo.destino.equals(destino) && vuelo.fecha.equals(fecha)) {
                vuelosSimilares.add(vuelo.codigo);
            }
        }

        return vuelosSimilares;
    }

    @Override
    public void cancelarPasaje(int dni, String codVuelo, int nroAsiento) {
        Vuelo vuelo = vuelos.get(codVuelo);

        if (vuelo != null && vuelo.asientos.containsKey(nroAsiento)) {
            vuelo.asientos.put(nroAsiento, false);
            System.out.println("Pasaje cancelado para el asiento " + nroAsiento);
        } else {
            System.out.println("Pasaje no encontrado.");
        }
    }

    @Override
    public double totalRecaudado(String destino) {
        // Suponiendo que cada pasaje vendido tenga un valor que podemos sumar
        double total = 0.0;
        for (Vuelo vuelo : vuelos.values()) {
            if (vuelo.destino.equals(destino)) {
            	total += vuelo.obtenerPrecioTotal();
            }
        }
        return total;
    }

    @Override
    public String detalleDeVuelo(String codVuelo) {
        Vuelo vuelo = vuelos.get(codVuelo);

        if (vuelo == null) {
            return "El vuelo no existe.";
        }

        return vuelo.toString();
    }
}

