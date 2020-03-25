/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author vanRz
 */
public class Tipo {
    private int k_idtipo ;
    private String n_nombre;
    private int k_evento; 

    public int getK_idtipo() {
        return k_idtipo;
    }

    public void setK_idtipo(int k_idtipo) {
        this.k_idtipo = k_idtipo;
    }

    public String getN_nombre() {
        return n_nombre;
    }

    public void setN_nombre(String n_nombre) {
        this.n_nombre = n_nombre;
    }

    public int getK_evento() {
        return k_evento;
    }

    public void setK_evento(int k_evento) {
        this.k_evento = k_evento;
    }
    
    
}
