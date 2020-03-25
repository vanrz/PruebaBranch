/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import negocio.Caracteristica;

import util.CaException;
import util.ServiceLocator;
import java.sql.*;

/**
 *
 * @author vanRz
 */
public class CaracteristicaDAO {
    
    Caracteristica car;

    public CaracteristicaDAO() {
        
        car= new Caracteristica();
    }
    public void buscarCaracteristica() throws CaException {
        try {
            String stringSQL = "SELECT * FROM Caracteristica WHERE k_evento = ?";//busqueda en sql
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);//prepara la busqueda del sql

            prepSta.setInt(1, car.getK_evento());//reemplaza el interrogante por el valor

            ResultSet resultado = prepSta.executeQuery();//ejecuta el query y guarda el resultado

            while (resultado.next()) {

                car.setK_evento(resultado.getInt(1));
                car.setO_tipopago(resultado.getString(2));
                car.setP_pago(resultado.getInt(3));
                car.setQ_cuotas(resultado.getInt(4));
                car.setV_copago(resultado.getInt(5));
               
            }

        } catch (SQLException e) {
            throw new CaException("CaracteristicaDAO", "No se encontró el evento" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public void AñadirAsociado() throws CaException {
        try {
            String stringSQL = "INSERT INTO Caracteristica VALUES (?,?,?,?,?)";
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);

            prepSta.setInt(1, car.getK_evento());
            prepSta.setString(2, car.getO_tipopago());
            prepSta.setInt(3, car.getP_pago());
            prepSta.setInt(4, car.getQ_cuotas());
            prepSta.setInt(5, car.getV_copago());
         
        } catch (SQLException e) {
            throw new CaException("CarecteristicaDAO", "No se creó el copago del evento" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }

    }

    
    
}
