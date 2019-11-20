/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sun.applet.Main;

/**
 *
 * @author
 */
public class PDF {

    public static void crearPDF(String reserva, String correo) {
        try {

            Document document = new Document();
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(document, new FileOutputStream(ruta + "/Desktop/FacturaCliente.pdf"));
            document.open();
            
            document.addTitle("Factura Ciente");
            document.addAuthor("Los Innombrables");
            document.add(new Paragraph("BIENVENIDO AL SISTEMA DE RENT A CAR"+"\n\n"));
            document.add(new Paragraph("Los datos de su reserva son los siguientes:"+"\n\n"));
            document.add(new Paragraph(reserva));
            document.close();
            
            Correo.enviarCorreoReserva(correo, ruta + "/Desktop/FacturaCliente.pdf");
            
            JOptionPane.showMessageDialog(null, "La factura PDF se ha enviado", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
