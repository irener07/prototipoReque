/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;

public class Conexion {
    static Connection contacto = null;
    
    public static Connection getConexion(){
        String url = "jdbc:sqlserver://FAMILIA-PICADO\\:1433;databaseName=PrototipoR";
        //El url es el nombre de la conexion a la base de datos que esta en servicio/Databases
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Se importa la libreria descargada y se copia el nombre que esta dentro de ella, al final se usa un
//            .SQLServerDriver
        }catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion, revisar driver" + e.getMessage(),
            "Error de conexion",JOptionPane.ERROR_MESSAGE);
        }
        try{
            contacto = DriverManager.getConnection(url,"sa","Abcd1234");
            System.out.print("Se conecto a la base de datos");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(),
            "Error de conexion",JOptionPane.ERROR_MESSAGE);
            
        }
        return contacto;
    }
    
    public static ResultSet consulta(String consulta){
        Connection con = getConexion();
        Statement declara;
        try{
            declara = con.createStatement();
            ResultSet respuesta = declara.executeQuery(consulta);
            return respuesta;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(),
            "Error de conexion",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }    
    
}
