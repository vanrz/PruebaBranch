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
public class Usuario {
    private int k_idusuario ;
    private String o_rol;
    private String o_contraseña;

    public int getK_idusuario() {
        return k_idusuario;
    }

    public void setK_idusuario(int k_idusuario) {
        this.k_idusuario = k_idusuario;
    }

    public String getO_rol() {
        return o_rol;
    }

    public void setO_rol(String o_rol) {
        this.o_rol = o_rol;
    }

    public String getO_contraseña() {
        return o_contraseña;
    }

    public void setO_contraseña(String o_contraseña) {
        this.o_contraseña = o_contraseña;
    }
    
    
}
