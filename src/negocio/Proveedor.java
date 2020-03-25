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
public class Proveedor {
    private int k_rut;
    private String n_nombre ;
    private String o_pagweb ;
    private String o_email ;
    private int o_tel ;
    private int k_evento;

    public int getK_rut() {
        return k_rut;
    }

    public void setK_rut(int k_rut) {
        this.k_rut = k_rut;
    }

    public String getN_nombre() {
        return n_nombre;
    }

    public void setN_nombre(String n_nombre) {
        this.n_nombre = n_nombre;
    }

    public String getO_pagweb() {
        return o_pagweb;
    }

    public void setO_pagweb(String o_pagweb) {
        this.o_pagweb = o_pagweb;
    }

    public String getO_email() {
        return o_email;
    }

    public void setO_email(String o_email) {
        this.o_email = o_email;
    }

    public int getO_tel() {
        return o_tel;
    }

    public void setO_tel(int o_tel) {
        this.o_tel = o_tel;
    }

    public int getK_evento() {
        return k_evento;
    }

    public void setK_evento(int k_evento) {
        this.k_evento = k_evento;
    }

    
}
