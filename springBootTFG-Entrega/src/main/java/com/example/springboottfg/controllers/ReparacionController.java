package com.example.springboottfg.controllers;

import com.example.springboottfg.FacturaPdfExporter;
import com.example.springboottfg.exceptions.NotFoundException;
import com.example.springboottfg.models.Cita;
import com.example.springboottfg.models.DatosUsuario;
import com.example.springboottfg.models.EstandarReparacion;
import com.example.springboottfg.models.Factura;
import com.example.springboottfg.repository.DatosUsuarioRepository;
import com.example.springboottfg.repository.EstandarReparacionRepository;
import com.example.springboottfg.repository.FacturaRepository;
import com.example.springboottfg.responses.CommonConstants;
import com.example.springboottfg.responses.TallerResponse;
import com.example.springboottfg.services.EstandarReparacionService;
import com.example.springboottfg.services.PdfService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reparaciones")
public class ReparacionController {


    @Autowired
    private EstandarReparacionService estandarReparacionService;

    @Autowired
    private EstandarReparacionRepository estandarReparacionRepository;

    @Autowired
    FacturaRepository facturaRepository;

    @Autowired
    PdfService pdfService;

    @Autowired
    DatosUsuarioRepository datosUsuarioRepository;

    @GetMapping
    public TallerResponse<List<EstandarReparacion>> getAllEstandarReparacion(){
        List<EstandarReparacion> er = estandarReparacionService.findAllEstandarReparacion();
        if (er.isEmpty()){
            return new TallerResponse<>(CommonConstants.ERROR,String.valueOf(HttpStatus.NOT_FOUND),"No se encontraron datos");
        }
        return new TallerResponse<>(CommonConstants.SUCCESS,String.valueOf(HttpStatus.OK),CommonConstants.OK,er);

    }


    @PostMapping(value = "/create")
    public TallerResponse<EstandarReparacion> crearEstandarReparacion(@Valid @RequestBody EstandarReparacion estandarReparacion,
                                                                      BindingResult result) throws NotFoundException {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        EstandarReparacion er = estandarReparacionService.crearEstandarReparacion(estandarReparacion);

        if (er == null){
            return new TallerResponse<>(CommonConstants.ERROR,String.valueOf(HttpStatus.NOT_FOUND),"No se encontraron datos");
        }

        return new TallerResponse<>(CommonConstants.SUCCESS,String.valueOf(HttpStatus.OK),CommonConstants.OK,er);
    }


    @DeleteMapping(value = "/{id}")
    public TallerResponse<EstandarReparacion> borrarEstandarReparacion(@PathVariable("id") Long idEstandar) throws NotFoundException {
        EstandarReparacion er = estandarReparacionRepository.findById(idEstandar).orElse(null);

        if (er == null){
            return new TallerResponse<>(CommonConstants.ERROR,String.valueOf(HttpStatus.NOT_FOUND),"No se encontraron datos");
        }
        estandarReparacionService.borrarEstandarReparacion(idEstandar);
        return new TallerResponse<>(CommonConstants.SUCCESS,String.valueOf(HttpStatus.OK),CommonConstants.OK);
    }


    @PutMapping("/{id}")
    public TallerResponse<EstandarReparacion> actualizarEstandarReparacion(@Valid @RequestBody EstandarReparacion estandarReparacion,
                                                                           BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        estandarReparacion.setId(id);
        EstandarReparacion er = estandarReparacionService.updateEstandarReparacion(estandarReparacion);
        if (er == null){
            return new TallerResponse<>(CommonConstants.ERROR,String.valueOf(HttpStatus.NOT_FOUND),"No se encontraron datos");
        }
        return new TallerResponse<>(CommonConstants.SUCCESS,String.valueOf(HttpStatus.OK),CommonConstants.OK, er);
    }

    @RequestMapping(value = "/export/pdf/{id}", method = RequestMethod.GET, produces = {"application/pdf"})
    @ResponseBody
    public void exportPdf(HttpServletResponse response, @PathVariable long id) throws DocumentException, IOException{
        Cita cita = pdfService.obtenerFactura(id);

        response.setContentType("application/pdf");


        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String nombre = cita.getTitle();

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename= Factura_"  + "_" + nombre + ".pdf";

        response.setHeader(cabecera, valor);

        DatosUsuario datosUsuario = datosUsuarioRepository.obtenerUsuarioid(cita.getId_usuario().getId());

        FacturaPdfExporter exporter = new FacturaPdfExporter(cita, datosUsuario);
        exporter.exportar(response);




//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//
//        FacturaPdfExporter exporter = new FacturaPdfExporter(cita);
//        exporter.export(response);
//



//        ByteArrayInputStream bais = pdfService.pdfReport(cita);
//        HttpHeaders header = new HttpHeaders();
//        header.add("Content-Disposition", "inline; filename=factura.pdf");
//        return ResponseEntity.ok().headers(header).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));


    }


}
