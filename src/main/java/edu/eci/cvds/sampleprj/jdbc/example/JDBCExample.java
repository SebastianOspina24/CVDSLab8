/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.sampleprj.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {
    
    public static void main(String args[]){
        try {
            String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="prueba2019";
                        
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);
                 
            
            System.out.println("Valor total pedido 1:"+valorTotalPedido(con, 1));
            
            List<String> prodsPedido=nombresProductosPedido(con, 1);
            
            
            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");
            
            
            int suCodigoECI=20134423;
            registrarNuevoProducto(con, suCodigoECI, "SU NOMBRE", 99999999);            
            con.commit();
                        
            
            con.close();
                                   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Agregar un nuevo producto con los parámetros dados
     * @param con la conexión JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException 
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio) throws SQLException{
        //Crear preparedStatement
        String nuevo = "insert into ORD_PRODUCTOS (codigo, nombre, precio) VALUES(?,?,?)";
        try{
            //Asignar parámetros
            //usar 'execute'
            PreparedStatement nuevoProducto = con.prepareStatement(nuevo);
            nuevoProducto.setInt(1, codigo);
            nuevoProducto.setString(2, nombre);
            nuevoProducto.setInt(3, precio);
            nuevoProducto.executeUpdate();
        }
        catch (SQLException e) {
            if (con != null) {
              try {
                System.err.print("Transaction is being rolled back");
                con.rollback();
              } catch (SQLException excep) {
              }
            }
        }
        
        con.commit();
        
    }
    
    /**
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexión JDBC
     * @param codigoPedido el código del pedido
     * @return 
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido){
        List<String> np=new LinkedList<>();
        //Crear prepared statement
        //asignar parámetros
        String consulta =  "select ORD_PRODUCTOS.nombre FROM ORD_PRODUCTOS JOIN ORD_DETALLES_PEDIDO ON (ORD_DETALLES_PEDIDO.producto_fk = ORD_PRODUCTOS.codigo) where ORD_DETALLES_PEDIDO.pedido_fk =" + codigoPedido;
        try{
            PreparedStatement productoPedido = con.prepareStatement(consulta);
             //usar executeQuery
            ResultSet rs = productoPedido.executeQuery(consulta);
            //Sacar resultados del ResultSet
            while(rs.next()){
                np.add(rs.getString("ORD_PRODUCTOS.nombre"));
            }
        }
        catch (SQLException e) {
            if (con != null) {
              try {
                System.err.print("Transaction is being rolled back");
                con.rollback();
              } catch (SQLException excep) {
              }
            }
        }
        //Llenar la lista y retornarla
        return np;
    }

    
    /**
     * Calcular el costo total de un pedido
     * @param con
     * @param codigoPedido código del pedido cuyo total se calculará
     * @return el costo total del pedido (suma de: cantidades*precios)
     */
    public static int valorTotalPedido(Connection con, int codigoPedido){
        String consulta = "select SUM(ORD_DETALLES_PEDIDO.cantidad * ORD_PRODUCTOS.precio) AS valor FROM ORD_PRODUCTOS JOIN ORD_DETALLES_PEDIDO ON (ORD_DETALLES_PEDIDO.producto_fk = ORD_PRODUCTOS.codigo) where ORD_DETALLES_PEDIDO.pedido_fk =" + codigoPedido;
        int valorTotal = 0;
        //Crear prepared statement
        //asignar parámetros
        try{
            PreparedStatement valorPedido = con.prepareStatement(consulta);
             //usar executeQuery
            ResultSet rs = valorPedido.executeQuery(consulta);
            //Sacar resultados del ResultSet
            while(rs.next()){
                valorTotal += rs.getInt("valor");
            }
        }
        catch (SQLException e) {
            if (con != null) {
              try {
                System.err.print("Transaction is being rolled back");
                con.rollback();
              } catch (SQLException excep) {
              }
            }
        }
        return valorTotal;
    }
    

    
    
    
}