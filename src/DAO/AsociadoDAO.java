/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import negocio.Asociado;
import util.CaException;
import util.ServiceLocator;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author vanRz
 */
public class AsociadoDAO {

    private Asociado aso;

    public AsociadoDAO() {
        aso = new Asociado();
    }//

    public void buscarAsociado() throws CaException {
        try {
            String stringSQL = "SELECT * FROM Asociado WHERE k_persona = ?";//busqueda en sql
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);//prepara la busqueda del sql

            prepSta.setInt(1, aso.getK_persona());//reemplaza el interrogante por el valor

            ResultSet resultado = prepSta.executeQuery();//ejecuta el query y guarda el resultado

            while (resultado.next()) {

                aso.setK_persona(resultado.getInt(1));
                aso.setN_npnombre(resultado.getString(2));
                aso.setN_nsnombre(resultado.getString(3));
                aso.setN_papellido(resultado.getString(4));
                aso.setN_sapelido(resultado.getString(5));
                Date f_nacimiento = resultado.getDate(6);
                aso.setF_nacimiento(f_nacimiento.toString());
                aso.setO_tel(resultado.getInt(7));
                aso.setO_email(resultado.getString(8));
                aso.setO_sexo(resultado.getString(9));
                aso.setO_tipoid(resultado.getString(10));
                aso.setV_sueldo(resultado.getInt(11));
                Date f_afiliacion = resultado.getDate(12);
                aso.setF_afiliacion(f_afiliacion.toString());
            }

        } catch (SQLException e) {
            throw new CaException("AsociadoDAO", "No se encontró el usuario" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public void AñadirAsociado() throws CaException {
        
        try {
            String stringSQL = "INSERT INTO \"Asociado\" (k_persona, n_npnombre, n_nsnombre, n_papellido, n_sapelido, f_nacimiento, o_tel, o_email, o_sexo, o_tipoid, v_sueldo, f_afiliacion) VALUES (?,?,?,?,?,?,?,?,?,?,?, CURRENT_DATE)";
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);

            prepSta.setInt(1, aso.getK_persona());
            prepSta.setString(2, aso.getN_npnombre());
            prepSta.setString(3, aso.getN_nsnombre());
            prepSta.setString(4, aso.getN_papellido());
            prepSta.setString(5, aso.getN_sapelido());
            prepSta.setDate(6, java.sql.Date.valueOf(aso.getF_nacimiento()));//yyyy-mm-dd
            prepSta.setInt(7, aso.getO_tel());
            prepSta.setString(8, aso.getO_email());
            prepSta.setString(9, aso.getO_sexo());
            prepSta.setString(10, aso.getO_tipoid());
            prepSta.setInt(11, aso.getV_sueldo());
            prepSta.executeUpdate();
            prepSta.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("AsociadoDAO", "No se creó el usuario" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        

    }

    public void ActualizarAsociado() throws CaException {

        try {
            String stringSQL = "UPDATE Asociado SET o_tel = ? WHERE k_persona= ? ";
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);
            
            prepSta.setInt(1, aso.getO_tel());
            prepSta.setInt(2, aso.getK_persona());
            
            prepSta.executeUpdate();
            prepSta.close();
            ServiceLocator.getInstance().commit();
            
        }catch (SQLException e) {
            throw new CaException("AsociadoDAO", "No se actualizo el usuario" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    
    

    

    public Asociado getAso() {
        return aso;
    }

    public void setAso(Asociado aso) {
        this.aso = aso;
    }

}
