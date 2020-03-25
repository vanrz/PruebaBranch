/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import util.CaException;
import util.ServiceLocator;
import java.sql.*;
import negocio.PagoProveedor;
/**
 *
 * @author vanRz
 */
public class PagoProveedorDAO {
    
    PagoProveedor pagProv;

    public PagoProveedorDAO() {
        
        pagProv= new PagoProveedor();
    }
    

    
    public void AñadirPagoProveedor() throws CaException{
          try {
            String stringSQL = "INSERT INTO pagoProveedor VALUES (?,?,?,?)";
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);
            
            prepSta.setInt(1, pagProv.getK_idpagoprovee());
            prepSta.setString(2, pagProv.getF_pagoprovee());//yyyy-mm-dd
            prepSta.setString(3, pagProv.getO_formapagoprovee());
            prepSta.setInt(4, pagProv.getK_rut());
            
         } catch (SQLException e) {
            throw new CaException("PagoProveedorDAO", "No se creó el pago al proveedor" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
    
}
