/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import negocio.Proveedor;

import util.CaException;
import util.ServiceLocator;
import java.sql.*;

/**
 *
 * @author vanRz
 */
public class ProveedorDAO {

    Proveedor prov;

    public ProveedorDAO() {
        prov = new Proveedor();
    }

    public void AñadirProveedor() throws CaException {
        try {
            String stringSQL = "INSERT INTO Proveedor VALUES (?,?,?,?,?,?)";
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);

            prepSta.setInt(1, prov.getK_rut());
            prepSta.setString(2, prov.getN_nombre());
            prepSta.setString(3, prov.getO_pagweb());
            prepSta.setString(4, prov.getO_email());
            prepSta.setInt(5, prov.getO_tel());
            prepSta.setInt(6, prov.getK_evento());

        } catch (SQLException e) {
            throw new CaException("ProveedorDAO", "No se creó el proveedor" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public void BorrarProveedor() throws CaException {
        try {
            String stringSQL = "DELETE FROM Proveedor WHERE k_rut= ?";//busqueda en sql
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);//prepara la busqueda del sql

            prepSta.setInt(1, prov.getK_rut());

            prepSta.executeUpdate();
            prepSta.close();

            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("ProveedorDAO", "No se eliminó el proveedor" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

}
