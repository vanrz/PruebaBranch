/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import negocio.Tipo;

import util.CaException;
import util.ServiceLocator;
import java.sql.*;

/**
 *
 * @author vanRz
 */
public class TipoDAO {

    Tipo tip;

    public TipoDAO() {
        tip = new Tipo();
    }


    public void AñadirTipo() throws CaException {
        try {
            String stringSQL = "INSERT INTO Tipo VALUES (?,?,?)";
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);

            prepSta.setInt(1, tip.getK_idtipo());
            prepSta.setString(2, tip.getN_nombre());
            prepSta.setInt(3, tip.getK_evento());

        } catch (SQLException e) {
            throw new CaException("TipoDAO", "No se creó el tipo de evento" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public void BorrarTipo() throws CaException {
        try {
            String stringSQL = "DELETE FROM tipo WHERE k_idtipo= ?";//busqueda en sql
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);//prepara la busqueda del sql

            prepSta.setInt(1, tip.getK_idtipo());

            prepSta.executeUpdate();
            prepSta.close();

            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("TipoDAO", "No se eliminó el tipo de evento" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

}
