/*
 * Descripción: Conexión con la base de datos
 * Autores: Alba consuelo nieto
 */
package util;

/**
 *
 * @author Alba consuelo nieto
 */
public class CaException extends Exception {

    private String detalle;
    private String clase;

    /**
     * @param error cadena que contiene las descripción del error.
     */
    public CaException(String clase, String error) {
        super(error);
        this.detalle = error;
        this.clase = clase;
        System.out.println(toString());
    }

    public String toString() {
        return "[" + clase + "] " + detalle;
    }

}

