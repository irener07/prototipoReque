/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.util.LinkedList;

/**
 *
 * @author Gabriel
 */
public class Compras {
    static LinkedList compraPrueba = new LinkedList();
    
    public static void simularCompra(){
        compraPrueba.add("1276");
        compraPrueba.add("12345678765432123");
        compraPrueba.add("23456787654321234567876543");
        compraPrueba.add("21/03/2019");
        compraPrueba.add("02:01 PM");
        compraPrueba.add("Reynaldo Picadillo Vega");
        compraPrueba.add("345678765");
        LinkedList productos = new LinkedList();
        String[] producto1 = new String[]{"2","Pilsen","6000"};
        String[] producto2 = new String[]{"3","Bavaria","8000"};
        String[] producto3 = new String[]{"10","Imperial","12000"};
        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);
        compraPrueba.add(productos);
        compraPrueba.add("26000");
        compraPrueba.add("0");
        compraPrueba.add("0");
        compraPrueba.add("26000");
        LinkedList pago = new LinkedList();
        String[] pagoInfo = new String[]{"26000","Tarjeta Credito","VISA 020578"};
        pago.add(pagoInfo);
        compraPrueba.add(pago);
    }
    
}
