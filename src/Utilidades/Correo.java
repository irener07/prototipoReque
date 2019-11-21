/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 *
 * @author
 */
public class Correo {

    public static void enviarCorreoFactura(String correo, String urlArchivo) {
        try {
            java.util.Date fecha = new Date();
            String remitente = "tareaprogramada2018ati@gmail.com";
            String clave = "aati2018";
            String asunto = "[Factura Electronica La Destileria]";
            String cuerpo = ("Bienvenido al sistema de Facturación de La Destileria" + "\n\n" + "Adjunto a este correo se encuentra los datos de la factura." + "\n" + fecha);
            String from = correo;
            
            BodyPart texto = new MimeBodyPart();
            texto.setText(cuerpo);
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(urlArchivo)));
            adjunto.setFileName("FacturaCliente.pdf");

            MimeMultipart multiParte = new MimeMultipart();

            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);

            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.user", remitente);
            props.put("mail.smtp.clave", clave);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);

            try {
                message.setFrom(new InternetAddress(remitente));
                message.setRecipients(Message.RecipientType.TO, from);
                message.setSubject(asunto);
                message.setContent(multiParte);
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", remitente, clave);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                System.out.println("EXITO");
            } catch (MessagingException me) {
                System.out.println("ERROR");
                me.printStackTrace();
            }
        } catch (MessagingException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}