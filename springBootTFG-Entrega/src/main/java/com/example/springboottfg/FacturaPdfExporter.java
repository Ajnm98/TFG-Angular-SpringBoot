package com.example.springboottfg;

import com.example.springboottfg.models.Cita;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.springboottfg.models.DatosUsuario;
import com.example.springboottfg.repository.DatosUsuarioRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class FacturaPdfExporter {
    private Cita cita;
    private DatosUsuario datosUsuario;

    public FacturaPdfExporter(Cita cita, DatosUsuario datos) {
        this.cita = cita;
        this.datosUsuario = datos;
    }

    private void escribirCabeceraHotel(PdfPTable tabla){
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(Color.RED);
        celda.setPadding(5);



        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("Datos Taller",fuente));
        tabla.addCell(celda);

    }
    private void escribirCabeceraCliente(PdfPTable tabla){
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(Color.RED);
        celda.setPadding(5);



        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("Datos Cliente",fuente));
        tabla.addCell(celda);

    }
    private void escribirCabeceraReserva(PdfPTable tabla){
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(Color.RED);
        celda.setPadding(5);



        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("Datos del Arreglo",fuente));
        tabla.addCell(celda);

    }

    private void escribirTablaReserva(PdfPTable tabla){

        tabla.addCell(String.valueOf("Fecha de inicio:" + " " + cita.getStart()));
        tabla.addCell(String.valueOf("Fecha de final:" + " " + cita.getEnd()));
        tabla.addCell(String.valueOf("Arreglo:" + " " + cita.getEstandarReparacion().getNombre()));
        tabla.addCell(String.valueOf("Descripcion:" + " " + cita.getEstandarReparacion().getDescripcion()));
        tabla.addCell(String.valueOf("Total a pagar:" + " " + cita.getEstandarReparacion().getPrecio_estimado()) + " " + "€");


    }

    private void escribirTablaCliente(PdfPTable tabla){
        tabla.addCell(String.valueOf("Nombre del Cliente:" + " " + datosUsuario.getNombre() + " " + datosUsuario.getApellidos()));
        tabla.addCell(String.valueOf("Dni:" + " " + datosUsuario.getDni()));
        tabla.addCell(String.valueOf("Teléfono del cliente:" + " " + datosUsuario.getTelefono()));
        tabla.addCell(String.valueOf("Email de contacto:" + " " + cita.getId_usuario().getEmail()));

    }



    private void escribirTablaHotel(PdfPTable tabla){
        tabla.addCell(String.valueOf("Nombre del taller: Taller Paco"));
        tabla.addCell(String.valueOf("Dirección: C/Calatra, 12, Sevilla" ));
        tabla.addCell(String.valueOf("Codigo postal:41010"));
        tabla.addCell(String.valueOf("Teléfono de contacto:954121582"));

    }


    public void exportar(HttpServletResponse response) throws IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento,response.getOutputStream());

        documento.open();

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.RED);
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Factura de la reserva", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(1);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[]{3f});
        tabla.setWidthPercentage(110);
        escribirCabeceraHotel(tabla);
        escribirTablaHotel(tabla);
        escribirCabeceraCliente(tabla);
        escribirTablaCliente(tabla);
        escribirCabeceraReserva(tabla);
        escribirTablaReserva(tabla);


        documento.add(tabla);
        documento.close();

    }



//    private void writeTableHeader(PdfPTable table) {
//        PdfPCell cell = new PdfPCell();
//        cell.setBackgroundColor(Color.gray);
//        cell.setPadding(5);
//
//        Font font = FontFactory.getFont(FontFactory.HELVETICA);
//        font.setColor(Color.WHITE);
//
//        cell.setPhrase(new Phrase("aaa", font));
//
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("E-mail", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("Full Name", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("Roles", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("Enabled", font));
//        table.addCell(cell);
//    }
//
//    private void writeTableData(PdfPTable table) {
//
//            table.addCell(cita.getId_usuario().getUsername());
//            table.addCell(cita.getId_usuario().getEmail());
//            table.addCell(cita.getEstandarReparacion().getDescripcion());
//            table.addCell(cita.getEstandarReparacion().getDuracion_estimada());
//            table.addCell(String.valueOf(cita.getEstandarReparacion().getPrecio_estimado()));
//    }
//
//    public void export(HttpServletResponse response) throws DocumentException, IOException {
//
//
//        Document document = new Document(PageSize.A4);
//        PdfWriter.getInstance(document, response.getOutputStream());
//        response.setContentType("application/pdf");
//
//        document.open();
//        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//        font.setSize(18);
//        font.setColor(Color.BLUE);
//
//        Paragraph p = new Paragraph("List of Users", font);
//        p.setAlignment(Paragraph.ALIGN_CENTER);
//
//        document.add(p);
//
//        PdfPTable table = new PdfPTable(5);
//        table.setWidthPercentage(100f);
//        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
//        table.setSpacingBefore(10);
//
//        writeTableHeader(table);
//        writeTableData(table);
//
//        document.add(table);
//
//        document.close();
//
//    }

}
