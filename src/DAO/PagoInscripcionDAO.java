/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import negocio.PagoInscripcion;

import negocio.Asociado;
import util.CaException;
import util.ServiceLocator;
import java.sql.*;

/**
 *
 * @author vanRz
 */
public class PagoInscripcionDAO {

    PagoInscripcion pagIns;

    public PagoInscripcionDAO() {
        pagIns = new PagoInscripcion();
    }


    public void AñadirPagoInscripcion() throws CaException {
        try {
            String stringSQL = "INSERT INTO PagoInscripcion VALUES (?,?,?,?,?)";
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);
            
            prepSta.setInt(1, pagIns.getK_pago());
            prepSta.setString(2, pagIns.getO_formapago());
            prepSta.setString(3, pagIns.getF_pago());//yyyy-mm-dd
            prepSta.setInt(4, pagIns.getV_pago());
            prepSta.setInt(5, pagIns.getK_ins());

        } catch (SQLException e) {
            throw new CaException("PagoInscripcionDAO", "No se creó el pago de la inscripcion" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
    
    
        //falta actualizar que se actualiza? valor de pago
    
    public void ActualizarPagoInscripcion() throws CaException{
        
         try {
            String stringSQL = "UPDATE PagoInscripcion SET v_pago = ? WHERE k_pago= ? ";
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);
            
            prepSta.setInt(1, pagIns.getV_pago());
            prepSta.setInt(2, pagIns.getK_ins());
            
            prepSta.executeUpdate();
            prepSta.close();
            ServiceLocator.getInstance().commit();
            
        }catch (SQLException e) {
            throw new CaException("PagoInscripcionDAO", "No se actualizo el pago de la inscripcion" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
}
