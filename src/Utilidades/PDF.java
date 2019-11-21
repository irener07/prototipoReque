/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.text.DecimalFormat;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sun.applet.Main;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
/**
 *
 * @author
 */
public class PDF {

    public static void crearPDF(LinkedList datosFactura, String correo) {
        try {

            Document document = new Document();
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(document, new FileOutputStream(ruta + "/Desktop/FacturaCliente.pdf"));
            document.open();
            
            document.addTitle("Bar La Destilería");
            document.addAuthor("Bar La Destilería");
            document.add(new Paragraph("Cédula Jurídica: 567654345678"+"\n\n"));
            document.add(new Paragraph("Teléfono: 24354356"+"\n\n"));
            document.add(new Paragraph("serviciocliente@ladestileria.com"+"\n\n"));
            document.add(new Paragraph("Provincia San Jose"+"\n\n"));
            document.add(new Paragraph("Cantón San Jose"+"\n\n"));
            document.add(new Paragraph("Distrito El Carmen"+"\n\n"));
            document.add(new Paragraph("Factura Electrónica No.  "+datosFactura.get(0)+"\n\n"));
            document.add(new Paragraph("Contado"+"\n\n"));
            document.add(new Paragraph("Clave:  "+datosFactura.get(1)+"\n\n"));
            document.add(new Paragraph("Cons:  "+datosFactura.get(2)+"\n\n"));
            document.add(new Paragraph("Fecha:  "+datosFactura.get(3)+"\n\n"));
            document.add(new Paragraph("Hora:  "+datosFactura.get(4)+"\n\n"));
            document.add(new Paragraph("Nombre:  "+datosFactura.get(5)+"\n\n"));
            document.add(new Paragraph("Cédula:  "+datosFactura.get(6)+"\n\n"));
            String[] columnas = new String[]{"Cantidad","Producto","Total"};
            PdfPTable tablaProductos = new PdfPTable(columnas.length);
            for (String columna : columnas) {
                PdfPCell cell = new PdfPCell();
                cell.setGrayFill(0.9f);
                cell.setPhrase(new Phrase(columna.toUpperCase()));
                tablaProductos.addCell(cell);
            }
            tablaProductos.completeRow();
            LinkedList productos = (LinkedList) datosFactura.get(7);
            for (Object producto : productos){
                String[] productoT = (String[]) producto;
                for (String datoProducto : productoT){
                    Phrase phrase = new Phrase(datoProducto);
                    tablaProductos.addCell(new PdfPCell(phrase));
                }
                tablaProductos.completeRow();
            }
            document.add(tablaProductos);
            document.add(new Paragraph("Sub Total:  "+datosFactura.get(8)+"\n\n"));
            document.add(new Paragraph("I.V.:  "+datosFactura.get(9)+"\n\n"));
            document.add(new Paragraph("Imp. Servicios:  "+datosFactura.get(10)+"\n\n"));
            document.add(new Paragraph("Total:  "+datosFactura.get(11)+"\n\n"));
            document.add(new Paragraph("Detalle de Pago"+"\n\n"));
            String[] columnasPago = new String[]{"Monto","TipoPago","Detalle"};
            PdfPTable tablaPago = new PdfPTable(columnasPago.length);
            for (String columnaPago : columnasPago) {
                PdfPCell cell = new PdfPCell();
                cell.setGrayFill(0.9f);
                cell.setPhrase(new Phrase(columnaPago.toUpperCase()));
                tablaPago.addCell(cell);
            }
            tablaPago.completeRow();
            LinkedList datosPago = (LinkedList) datosFactura.get(12);
            for (Object datoPago : datosPago){
                String[] datoPagoT = (String[]) datoPago;
                for (String datoPagoTP : datoPagoT){
                    Phrase phrase = new Phrase(datoPagoTP);
                    tablaPago.addCell(new PdfPCell(phrase));
                }
                tablaPago.completeRow();
            }
            document.add(tablaPago);
            document.close();
            
            Correo.enviarCorreoReserva(correo, ruta + "/Desktop/FacturaCliente.pdf");
            
            JOptionPane.showMessageDialog(null, "La factura PDF se ha enviado", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
