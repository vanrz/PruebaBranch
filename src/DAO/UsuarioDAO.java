/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import util.CaException;
import util.ServiceLocator;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import negocio.Usuario;

/**
 *
 * @author vanRz
 */
public class UsuarioDAO {

    private Usuario usu;
    

    

    public UsuarioDAO() {

        usu = new Usuario();
    }

    public void AñadirUsuario() throws CaException {
        try {
            String stringSQL = "INSERT INTO \"Usuario\" (k_idusuario, o_rol, o_contraseña) VALUES (?,?,?)";
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);

            prepSta.setInt(1, usu.getK_idusuario());
            prepSta.setString(2, usu.getO_rol());
            prepSta.setString(3, usu.getO_contraseña());
            prepSta.executeUpdate();
            prepSta.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("UsuarioDAO", "No se creó el usuario" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
    
    public boolean BuscarUsuario() throws CaException {
        try {
            String stringSQL = "SELECT * FROM \"Usuario\" WHERE k_idusuario=? AND o_contraseña=? AND o_rol=?";
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);

            prepSta.setInt(1, usu.getK_idusuario());
            prepSta.setString(2, usu.getO_contraseña());
            prepSta.setString(3, usu.getO_rol());
            
            ResultSet rs = prepSta.executeQuery();
            if (rs.next()){
                return true;
            }
            else{
                return false;
            }
            
        
        } catch (SQLException e) {
            throw new CaException("UsuarioDAO", "error" + e.getMessage());
            
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
    
    
    
    
    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }
}
