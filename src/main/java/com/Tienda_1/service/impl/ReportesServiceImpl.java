/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.Tienda_1.service.ReportesService;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIOW10
 */
@Service
public class ReportesServiceImpl implements ReportesService{
    
    @Autowired
    DataSource dataSource;
    
    @Override
    public ResponseEntity<Resource> generarReporte(String reporte, Map<String, Object> parametros, String tipo) throws IOException{
        try {
            String estilo;
            if ("vPdf".equals(tipo)){
                estilo ="inline; ";
                
            }else{
                estilo ="attachment; ";
            }
            
            String reportePath = "reportes";
            
            ByteArrayOutputStream salida = new ByteArrayOutputStream();
            
            ClassPathResource fuente = new ClassPathResource(reportePath + File.separator + reporte + ".jasper");
            
            InputStream elReporte = fuente.getInputStream();
            
            var reporteJasper = JasperFillManager.fillReport(elReporte, parametros, dataSource.getConnection());
        
            MediaType mediaType = null;
            String archivoSalida = "";
            byte [] data;
            
            if(tipo != null){
                switch (tipo) {
                    case "Pdf", "vPdf"->{
                        JasperExportManager.exportReportToPdfStream(reporteJasper, salida);
                        mediaType = MediaType.APPLICATION_PDF;
                        archivoSalida = reporte + ".pdf";
                    }
                    case "Xls"->{
                        
                    }
                    case "Csv"->{
                        
                    }
                }
            }
        
            data = salida.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Disposition", estilo + "filename=\"" + archivoSalida + "\"");
            
            return ResponseEntity 
                    .ok()
                    .headers(headers)
                    .contentLength(data.length)
                    .contentType(mediaType)
                    .body(new InputStreamResource(new ByteArrayInputStream(data)));
            
        } catch (SQLException | JRException ex) {
            ex.printStackTrace();
            return null;
        } 
        
    }
    
}
