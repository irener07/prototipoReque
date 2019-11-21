/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Blob;
import java.text.ParseException;

public class Procedimientos {

    public static void ingresoFactura(String a, String b, String c, String d, Blob e, String f) throws SQLException {
        CallableStatement factura = Conexion.getConexion().prepareCall("{call ingresarFactura(?,?,?,?,?,?)}");
        factura.setString(1, a);
        factura.setString(2, b);
        factura.setString(3, c);
        factura.setString(4, d);
        factura.setBlob(5, e);
        factura.execute();
    }


    public static void buscarFactura(int a) throws SQLException {
        CallableStatement idFactura = Conexion.getConexion().prepareCall("{call buscarFactura(?)}");
        idFactura.setInt(1, a);
        idFactura.execute();
    }

}
