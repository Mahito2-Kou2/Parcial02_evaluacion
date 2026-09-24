package co.edu.usc.voltacali;

/* INTENTO 2: Hello world!_SE BORRO EL ANTERIRO*/

/**Clase**/

public class CargadorVE {

/** Atributos**/

private String fabricante;
private String anioIntalacion;
private String voltajeNominal;
private TipoCargador tipoCargador;
private TipoConector tipoConector;
private static int totalCargadores = 0;
private static int contadorRegistros = 0;
public static final double LIMITE_RED = 50.0;
public static final double INCREMENTO_DEFECTO = 5.0;


/* Punto D*/
public enum TipoConector {
    TTIPO_1, TIPO_2, CCS2, CHADEMO, GBT
}

public enum TipoCOnector {
    MURAL, PEDESTAL, RAPIDO_DC, ULTRARRAPIDO, PORTATIL, BIDIRECCIONAL_V2G
}

public enum Ubicacion {
    CENTRO_COMERCIAL, UNIVERSIDAD, ESTACION_SERVICIO, PARQUEADERO_PUBLICO, RESIDENCIAL, HOTEL, TERMINAL, FLOTA_CORPORATIVA
}



}

public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
