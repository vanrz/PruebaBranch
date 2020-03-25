/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import negocio.Inscripcion;

import util.CaException;
import util.ServiceLocator;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vanRz
 */
public class InscripcionDAO {

    
    private DefaultTableModel modelo;
    private Inscripcion ins;

    public Inscripcion getIns() {
        return ins;
    }

    public void setIns(Inscripcion ins) {
        this.ins = ins;
    }

    public InscripcionDAO() {

        ins = new Inscripcion();
    }

    public void AñadirInscripcion() throws CaException {
        try {
            String stringSQL = "INSERT INTO \"Inscripcion\" (k_ins, i_estado, f_ins, v_ins, i_asistencia, k_persona, k_evento) VALUES (?,?,CURRENT_DATE,?,?,?,?)";
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);

            prepSta.setInt(1, ins.getK_ins());
            prepSta.setString(2, ins.getI_estado());
            //prepSta.setString(3, ins.getF_ins());//yyyy-mm-dd
            prepSta.setInt(3, ins.getV_ins());
            prepSta.setString(4, ins.getI_asistencia());
            prepSta.setInt(5, ins.getK_persona());
            prepSta.setInt(6, ins.getK_evento());
            prepSta.executeUpdate();
            prepSta.close();
            ServiceLocator.getInstance().commit();
            
            
        } catch (SQLException e) {
            throw new CaException("InscripcionDAO", "No se creó la inscripcion" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public DefaultTableModel BuscarInscripcion() throws CaException {
        
        String datos[]= new String[4];
        modelo= new DefaultTableModel();
        modelo.addColumn("ID Asociado");
        modelo.addColumn("ID familiar");
        modelo.addColumn("ID Inscripcion");
        modelo.addColumn("estadoIns");
        
        try {
            String stringSQL = "SELECT \"Asociado\".k_persona, k_familiar, \"Inscripcion\".k_ins, i_estado  FROM \"Inscripcion\", \"DetalleInscripcion\", \"Asociado\" WHERE k_evento = ? AND \"Asociado\".k_persona=? AND \"DetalleInscripcion\".k_ins=\"Inscripcion\".k_ins AND \"Inscripcion\".k_persona=\"Asociado\".k_persona";//busqueda en sql
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);//prepara la busqueda del sql

            prepSta.setInt(1, ins.getK_evento());//reemplaza el interrogante por el valor
            prepSta.setInt(2, ins.getK_persona());
            ResultSet resultado = prepSta.executeQuery();//ejecuta el query y guarda el resultado

            while (resultado.next()) {
                datos[0]=resultado.getString(1);
                datos[1]=resultado.getString(2);
                datos[2]=resultado.getString(3);
                datos[3]=resultado.getString(4);
                modelo.addRow(datos);
            }

        } catch (SQLException e) {
            throw new CaException("InscripcionDAO", "No se encontro la inscripcion" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        return modelo;
    }

    public void BorrarInscripcion() throws CaException {
        try {
            String stringSQL = "DELETE FROM Inscripcion WHERE k_ins = ?";//busqueda en sql
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);//prepara la busqueda del sql

            prepSta.setInt(1, ins.getK_ins());

            prepSta.executeUpdate();
            prepSta.close();

            ServiceLocator.getInstance().commit();
            
        } catch (SQLException e) {
            throw new CaException("InscripcionDAO", "No se eliminó la inscripcion" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
    

}
