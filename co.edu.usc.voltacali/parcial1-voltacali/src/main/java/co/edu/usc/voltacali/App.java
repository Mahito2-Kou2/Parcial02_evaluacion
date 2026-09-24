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

public enum TipoCargador{
    MURAL, PEDESTAL, RAPIDO_DC, ULTRARRAPIDO, PORTATIL, BIDIRECCIONAL_V2G
}

public enum Ubicacion {
    CENTRO_COMERCIAL, UNIVERSIDAD, ESTACION_SERVICIO, PARQUEADERO_PUBLICO, RESIDENCIAL, HOTEL, TERMINAL, FLOTA_CORPORATIVA
}

public class RegistroSesion {
    private final int numero;
    private final String evento;
    private final boolean valido;
    private final string fabricante;
    private final int anioInstalacion;
    private final double potenciaActual;

    public RegistroSesion(String evento, boolean valido){
        this.evento = evento;
        this.valido = valido;
        this.fabricante = CargadorVE.this.fabricante;
        this.anioInstalacion = CargadorVE.this.anioInstalacion;
        this.potenciaActual = CargadorVE.this.potenciaActual;
        
        /** Contador **/
        contadorRegistros++;
        this.numero = contadorRegistros;
    
    }

    public String describir(){
        return "-" + numero + "-" + fabricante + "(" + anioInstalacion + ")"
    + " | " + potenciaActual + "kw"
    + " | " + (valido ? "VALIDO" : "INVADO")
    + " | " + evento;

    } 

    public CargadorVE(String fabricante, int anioInstalacion, int voltajeNominal, TipoConector tipoConector, TipoCargador tipocargador, int numeroConectores, int puestosParqueo, double potenciaMaxima, Ubicacion ubicacion){
    this.fabricante = fabricante;
    this.anioInstalacion = anioInstalacion;
    this.voltajeNominal = voltajeNominal;
    this.tipoConector = tipoConector;
    this.puestosParqueo = puestosParqueo;
    this.potenciaMaxima = potenciaMaxima;
    this.ubicacion = ubicacion;
    this.potenciaActual = 0;

    /**Contador**/
    
    }

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
