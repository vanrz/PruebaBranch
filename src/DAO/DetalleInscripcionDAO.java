/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;




import util.CaException;
import util.ServiceLocator;
import java.sql.*;
import negocio.DetalleInscripcion;

/**
 *
 * @author vanRz
 */
public class DetalleInscripcionDAO {

    private DetalleInscripcion detIns;

    public DetalleInscripcion getDetIns() {
        return detIns;
    }

    public void setDetIns(DetalleInscripcion detIns) {
        this.detIns = detIns;
    }

    public DetalleInscripcionDAO() {

        detIns = new DetalleInscripcion();
    }
    
    public void AñadirDetalleIns() throws CaException {
        try {
            String stringSQL = "INSERT INTO \"DetalleInscripcion\" (k_identificacion, k_ins, k_familiar) VALUES (?,?,?)";
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);

            prepSta.setInt(1, detIns.getK_identificacion());
            prepSta.setInt(2, detIns.getK_ins());
            prepSta.setInt(3, detIns.getK_familiar());
            prepSta.executeUpdate();
            prepSta.close();
            ServiceLocator.getInstance().commit();

        } catch (SQLException e) {
            throw new CaException("AsociadoDAO", "No se creó el usuario" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }

    }

    public void buscarDetalleIns() throws CaException {
        try {
            String stringSQL = "SELECT * FROM DetalleInscripcion WHERE k_identificacion= ?";//busqueda en sql
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);//prepara la busqueda del sql

            prepSta.setInt(1, detIns.getK_identificacion());//reemplaza el interrogante por el valor

            ResultSet resultado = prepSta.executeQuery();//ejecuta el query y guarda el resultado

            while (resultado.next()) {

                detIns.setK_identificacion(resultado.getInt(1));
                detIns.setK_ins(resultado.getInt(2));
                detIns.setK_familiar(resultado.getInt(3));

            }

        } catch (SQLException e) {
            throw new CaException("DetalleInscripcionDAO", "No se encontró el detFamiliar" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public void borrarDetalleIns() throws CaException {
        try {
            String stringSQL = "DELETE FROM DetalleInscripcion WHERE k_identificacion= ?";//busqueda en sql
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);//prepara la busqueda del sql
            
            prepSta.setInt(1, detIns.getK_identificacion());

            prepSta.executeUpdate();
            prepSta.close();

            ServiceLocator.getInstance().commit();
        }catch (SQLException e) {
            throw new CaException("DetalleInscripcionDAO", "No se eliminó el detFamiliar" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

}
