/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import negocio.Evento;

import util.CaException;
import util.ServiceLocator;
import java.sql.*;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vanRz
 */
public class EventoDAO {

    private Evento evt;
    
    private DefaultTableModel modelo;

    public Evento getEvt() {
        return evt;
    }

    public void setEvt(Evento evt) {
        this.evt = evt;
    }

    public EventoDAO() {

        evt = new Evento();
    }

    public void AñadirEvento() throws CaException {
        try {
            String stringSQL = "INSERT INTO \"Evento\" (k_evento, i_estado, i_sobrecupo, f_inicio, f_fin, f_maxins, f_maxcancel, f_cierre, i_tieneins, v_total, n_lugar, n_descripcion, n_nombre, o_observaciones, q_maxpart) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);

            prepSta.setInt(1, evt.getK_evento());
            prepSta.setString(2, evt.getI_estado());
            prepSta.setString(3, evt.getI_sobrecupo());
            prepSta.setString(4, evt.getF_inicio());//yyyy-mm-dd
            prepSta.setString(5, evt.getF_fin());//yyyy-mm-dd
            prepSta.setString(6, evt.getF_maxins());//yyyy-mm-dd
            prepSta.setString(7, evt.getF_maxcancel());//yyyy-mm-dd
            prepSta.setString(8, evt.getF_cierre());//yyyy-mm-dd
            prepSta.setString(9, evt.getI_tieneins());
            prepSta.setInt(10, evt.getV_total());
            prepSta.setString(11, evt.getN_lugar());
            prepSta.setString(12, evt.getN_descripcion());
            prepSta.setString(13, evt.getN_nombre());
            prepSta.setString(14, evt.getO_observaciones());
            prepSta.setInt(15, evt.getQ_maxpart());
            
            prepSta.executeUpdate();
            prepSta.close();
            ServiceLocator.getInstance().commit();

        } catch (SQLException e) {
            throw new CaException("EventoDAO", "No se creó el evento" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }

    }
    
    public DefaultTableModel mostrarEventos() throws CaException {
        
        ArrayList <Integer> inscritos= new ArrayList<Integer>();
        ArrayList <Integer> k_evento= new ArrayList<Integer>();
        inscritos.add(0);
        k_evento.add(0);
        
            String datos[]= new String[6];
            modelo= new DefaultTableModel();
            modelo.addColumn("codigo");
            modelo.addColumn("nombre");
            modelo.addColumn("cupos dis");
            modelo.addColumn("tipo");
            modelo.addColumn("fecha");
            modelo.addColumn("valor");
            
            
       
        try {
            String stringSQL = "SELECT \"Evento\".k_evento, COUNT(k_ins)  FROM \"Evento\", \"Inscripcion\" WHERE \"Evento\".k_evento = \"Inscripcion\".k_evento AND \"Inscripcion\".i_estado='S' GROUP BY \"Evento\".k_evento";//busqueda en sql
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);//prepara la busqueda del sql

            ResultSet resultado = prepSta.executeQuery();//ejecuta el query y guarda el resultado
                 
            while (resultado.next()) {
                k_evento.add(Integer.parseInt(resultado.getString(1)));
                inscritos.add(Integer.parseInt(resultado.getString(2)));
            }

        } catch (SQLException e) {
            throw new CaException("EventoDAO", "No se encontró el evento" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }  
        
        int cont=0;
       try {
            String stringSQL1= "SELECT \"Evento\".k_evento, \"Evento\".n_nombre, q_maxpart, \"Tipo\".n_nombre, f_fin, ((v_total/q_maxpart)*(1-(p_pago/100)))  FROM  \"Evento\", \"Caracteristica\", \"Tipo\" WHERE  \"Evento\".k_evento=\"Caracteristica\".k_evento AND \"Evento\".k_evento=\"Tipo\".k_evento";
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL1);
             ResultSet resultado = prepSta.executeQuery();//ejecuta el query y guarda el resultado
             
             while(resultado.next()){
                 datos[0]= resultado.getString(1);
                 datos[1]= resultado.getString(2);
                 datos[2]= resultado.getString(3);
                 do{
                     if(k_evento.get(cont)==Integer.parseInt(resultado.getString(1)))
                         datos[2]= (Integer.parseInt(resultado.getString(3))-inscritos.get(cont)+"");  
                      cont++;
                      System.out.println(k_evento.size()+" "+cont);
                 }while(k_evento.size()>cont);            
                 
                 datos[3]= resultado.getString(4);
                 datos[4]= resultado.getString(5);
                 datos[5]=resultado.getString(6);
                 cont++;
                 modelo.addRow(datos);
              cont=0;
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
            
       
        return modelo;
    }

    public void buscarEvento() throws CaException {
        try {
            String stringSQL = "SELECT * FROM Evento WHERE k_evento = ?";//busqueda en sql
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);//prepara la busqueda del sql

            prepSta.setInt(1, evt.getK_evento());//reemplaza el interrogante por el valor

            ResultSet resultado = prepSta.executeQuery();//ejecuta el query y guarda el resultado

            while (resultado.next()) {

                evt.setK_evento(resultado.getInt(1));
                evt.setI_estado(resultado.getString(2));
                evt.setI_sobrecupo(resultado.getString(3));
                Date f_inicio = resultado.getDate(4);
                evt.setF_inicio(f_inicio.toString());//yyyy-mm-dd
                Date f_fin = resultado.getDate(5);
                evt.setF_fin(f_fin.toString());//yyyy-mm-dd
                Date f_maxins = resultado.getDate(6);
                evt.setF_maxins(f_maxins.toString());//yyyy-mm-dd
                Date f_maxcancel = resultado.getDate(7);
                evt.setF_maxcancel(f_maxcancel.toString());//yyyy-mm-dd
                Date f_cierre = resultado.getDate(8);
                evt.setF_cierre(f_cierre.toString());//yyyy-mm-dd
                evt.setI_tieneins(resultado.getString(9));
                evt.setV_total(resultado.getInt(10));
                evt.setN_lugar(resultado.getString(11));
                evt.setN_descripcion(resultado.getString(12));
                evt.setN_nombre(resultado.getString(13));
                evt.setO_observaciones(resultado.getString(14));
                evt.setQ_maxpart(resultado.getInt(15));

            }

        } catch (SQLException e) {
            throw new CaException("EventoDAO", "No se encontró el evento" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public void borrarEvento() throws CaException {
        try {
            String stringSQL = "DELETE FROM Evento WHERE k_evento= ?";//busqueda en sql
            Connection conex = ServiceLocator.getInstance().tomarConexion();//conexion
            PreparedStatement prepSta = conex.prepareStatement(stringSQL);//prepara la busqueda del sql

            prepSta.setInt(1, evt.getK_evento());

            prepSta.executeUpdate();
            prepSta.close();

            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("EventoDAO", "No se eliminó el evento" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

}
