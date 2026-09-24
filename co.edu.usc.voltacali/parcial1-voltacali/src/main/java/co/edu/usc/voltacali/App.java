package co.edu.usc.voltacali;

/* INTENTO 2: Hello world!_SE BORRO EL ANTERIRO*/

public class CargadorVE {

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
