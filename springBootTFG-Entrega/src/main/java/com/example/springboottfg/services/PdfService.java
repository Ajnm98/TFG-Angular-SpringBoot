package com.example.springboottfg.services;


import com.example.springboottfg.models.Cita;
import com.example.springboottfg.models.Factura;
import com.example.springboottfg.models.Mecanico;
import com.example.springboottfg.repository.CitaRepository;
import com.example.springboottfg.repository.FacturaRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class PdfService {

    @Autowired
    FacturaRepository facturaRepository;

    @Autowired
    CitaRepository citaRepository;

    public static ByteArrayInputStream pdfReport(Cita cita) {
        Document documento = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(documento, out);
            documento.open();

            Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fuente.setColor(Color.RED);
            fuente.setSize(18);

            Paragraph titulo = new Paragraph("Factura del arreglo", fuente);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);

            PdfPTable tabla = new PdfPTable(1);
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(15);
            tabla.setWidths(new float[]{3f});
            tabla.setWidthPercentage(110);

            PdfPCell celda = new PdfPCell();
            celda.setBackgroundColor(Color.RED);
            celda.setPadding(5);

            Font fuente1 = FontFactory.getFont(FontFactory.HELVETICA);
            fuente1.setColor(Color.WHITE);

            celda.setPhrase(new Phrase("Datos Taller", fuente1));
            tabla.addCell(celda);

            PdfPCell celda2 = new PdfPCell();
            celda2.setBackgroundColor(Color.RED);
            celda2.setPadding(5);


            Font fuente2 = FontFactory.getFont(FontFactory.HELVETICA);
            fuente2.setColor(Color.WHITE);

            celda2.setPhrase(new Phrase("Datos Cliente", fuente2));
            tabla.addCell(celda2);

            PdfPCell celda3 = new PdfPCell();
            celda3.setBackgroundColor(Color.RED);
            celda3.setPadding(5);


            Font fuente3 = FontFactory.getFont(FontFactory.HELVETICA);
            fuente3.setColor(Color.WHITE);

            celda3.setPhrase(new Phrase("Datos del Arreglo", fuente3));
            tabla.addCell(celda3);

            tabla.addCell(String.valueOf("Fecha de pago:" + " " + Date.valueOf(LocalDate.now())));

            tabla.addCell(String.valueOf("Duración (estimada):" + " " + cita.getEstandarReparacion().getDuracion_estimada()));

            tabla.addCell(String.valueOf("Total a pagar:" + " " + cita.getEstandarReparacion().getPrecio_estimado() + " " + "€"));

            tabla.addCell(String.valueOf("Usuario Cliente:" + " " + cita.getId_usuario().getUsername()));
            tabla.addCell(String.valueOf("Email Cliente:" + " " + cita.getId_usuario().getEmail()));

            documento.add(tabla);
            documento.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    public Cita obtenerFactura(long id){
        Cita factura1 = citaRepository.findCita(id);
        return factura1;
    }

}
