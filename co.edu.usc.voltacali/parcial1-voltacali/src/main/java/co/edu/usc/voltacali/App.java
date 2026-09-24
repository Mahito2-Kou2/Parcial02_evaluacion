package co.edu.usc.voltacali;

import java.util.Vector;

/** Clase principal del cargador VE. */
class CargadorVE {
    /** Atributos */
    private String fabricante;
    private int anioInstalacion;
    private int voltajeNominal;
    private TipoCargador tipoCargador;
    private TipoConector tipoConector;
    private int numeroConectores;
    private int puestosParqueo;
    private double potenciaMaxima;
    private Ubicacion ubicacion;
    private double potenciaActual;

    private static int totalCargadores = 0;
    private static int contadorRegistros = 0;

    public static final double LIMITE_RED = 50.0;
    public static final double INCREMENTO_DEFECTO = 5.0;

    private final Vector<RegistroSesion> registro = new Vector<>();

    public enum TipoConector {
        TTIPO_1, TIPO_2, CCS2, CHADEMO, GBT
    }

    public enum TipoCargador {
        MURAL, PEDESTAL, RAPIDO_DC, ULTRARRAPIDO, PORTATIL, BIDIRECCIONAL_V2G
    }

    public enum Ubicacion {
        CENTRO_COMERCIAL, UNIVERSIDAD, ESTACION_SERVICIO, PARQUEADERO_PUBLICO,
        RESIDENCIAL, HOTEL, TERMINAL, FLOTA_CORPORATIVA
    }

    public class RegistroSesion {
        private final int numero;
        private final String evento;
        private final boolean valido;
        private final String fabricante;
        private final int anioInstalacion;
        private final double potenciaActual;

        public RegistroSesion(String evento, boolean valido) {
            this.evento = evento;
            this.valido = valido;
            this.fabricante = CargadorVE.this.fabricante;
            this.anioInstalacion = CargadorVE.this.anioInstalacion;
            this.potenciaActual = CargadorVE.this.potenciaActual;

            contadorRegistros++;
            this.numero = contadorRegistros;
        }

        public String describir() {
            return "-" + numero + "-" + fabricante + "(" + anioInstalacion + ")"
                    + " | " + potenciaActual + "kw"
                    + " | " + (valido ? "VALIDO" : "INVALIDO")
                    + " | " + evento;
        }
    }

    public CargadorVE(String fabricante, int anioInstalacion, int voltajeNominal, TipoConector tipoConector, TipoCargador tipoCargador, int numeroConectores, int puestosParqueo, double potenciaMaxima, Ubicacion ubicacion) {
        this.fabricante = fabricante;
        this.anioInstalacion = anioInstalacion;
        this.voltajeNominal = voltajeNominal;
        this.tipoConector = tipoConector;
        this.tipoCargador = tipoCargador;
        this.numeroConectores = numeroConectores;
        this.puestosParqueo = puestosParqueo;
        this.potenciaMaxima = potenciaMaxima;
        this.ubicacion = ubicacion;
        this.potenciaActual = 0;
        totalCargadores++;
    }

    public CargadorVE(String fabricante, int anioInstalacion, double potenciaMaxima) {
        this(fabricante, anioInstalacion, 220, TipoConector.TIPO_2, TipoCargador.PEDESTAL, 1, 1, potenciaMaxima, Ubicacion.PARQUEADERO_PUBLICO);
    }

    public String getFabricante() {
        return fabricante;
    }

    public int getAnioInstalacion() {
        return anioInstalacion;
    }

    public int getVoltajeNominal() {
        return voltajeNominal;
    }

    public TipoCargador getTipoCargador() {
        return tipoCargador;
    }

    public TipoConector getTipoConector() {
        return tipoConector;
    }

    public int getNumeroConectores() {
        return numeroConectores;
    }

    public int getPuestosParqueo() {
        return puestosParqueo;
    }

    public double getPotenciaMaxima() {
        return potenciaMaxima;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public double getPotenciaActual() {
        return potenciaActual;
    }

    public static int getTotalCargadores() {
        return totalCargadores;
    }

    public String describir() {
        return "CargadorVE{"
                + "fabricante='" + fabricante + '\''
                + ", anioInstalacion=" + anioInstalacion
                + ", voltajeNominal=" + voltajeNominal
                + ", tipoCargador=" + tipoCargador
                + ", tipoConector=" + tipoConector
                + ", numeroConectores=" + numeroConectores
                + ", puestosParqueo=" + puestosParqueo
                + ", potenciaMaxima=" + potenciaMaxima
                + ", ubicacion=" + ubicacion
                + ", potenciaActual=" + potenciaActual
                + '}';
    }

    public void ajustarPotencia(double incremento) {
        if (incremento < 0) {
            throw new IllegalArgumentException("El incremento no puede ser negativo.");
        }
        potenciaActual = Math.min(potenciaActual + incremento, potenciaMaxima);
        registrarEvento("Ajuste de potencia: +" + incremento + " kw", true);
    }

    public boolean puedeOperar() {
        return potenciaActual <= potenciaMaxima && potenciaMaxima > 0;
    }

    public void registrarEvento(String evento, boolean valido) {
        registro.add(new RegistroSesion(evento, valido));
    }

    public Vector<RegistroSesion> getRegistro() {
        return registro;
    }

 private boolean cambiar(double nueva, String evento) {
        boolean valido = nueva >= 0 && nueva <= potenciaMaxima;
        if (valido) {
            potenciaActual = nueva;
        } else {
            System.out.println("   Rechazado: " + evento + " daria " + nueva + " kW");
            evento = evento + " rechazado";
        }
        registro.add(new RegistroSesion(evento, valido));
        return valido;
    }


    public boolean setPotenciaActual(double p) {
        return cambiar(p, "setPotenciaActual(" + p + ")");
    }
 
    public boolean aumentarPotencia(double incremento) {
        return cambiar(potenciaActual + incremento, "aumentarPotencia(" + incremento + ")");
    }
 
    public boolean aumentarPotencia() {
        return aumentarPotencia(INCREMENTO_DEFECTO);
    }

    public int aumentarPotencia(double incremento, int veces) {
        int aplicados = 0;
            while (aplicados < veces && aumentarPotencia(incremento)) {
            aplicados++;
        }
        return aplicados;
    }

    public boolean reducirPotencia(double decremento) {
        return cambiar(potenciaActual - decremento, "reducirPotencia(" + decremento + ")");
    }

    public void cortarCarga() {
    cambiar(0, "cortarCarga()");
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
}
