/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.report;
import Service.ComprobanteService;
import Service.MovieService;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author mario
 */
public class reporteController {
    @Inject
     ServletContext context;
    @EJB
    ComprobanteService cService;
     @PersistenceContext(unitName = "WsCineUNAPU")
     private EntityManager em;
    
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    public reporteController(ServletContext context) {
        this.context = context;
    }
    
      public Respuesta ganerateJasperReport(List<Movie> l) throws JRException, FileNotFoundException, IOException {
          reportMovie r ;
          List<reportMovie> rM = new ArrayList<>();
          Integer i;
          try {
         for(Movie m : l){
           
           r = new reportMovie(m.getMovieNombre(),m.getComprobanteList().size());
           rM.add(r);
         } 
          Collections.sort(rM, (reportMovie p1, reportMovie p2) -> { 
                return p2.getCantidad().compareTo(p1.getCantidad());
            } 
            );
          
         String ruta = context.getRealPath("/");
         String JasperRuta = ruta+ "\\jasper\\movies.jrxml";
         String pdfRuta = ruta+ "\\jasper\\jasperPrueba.pdf";
         String outPutFile = pdfRuta;
       
         JRBeanCollectionDataSource cobrojrb = new JRBeanCollectionDataSource(rM);
            JasperReport jasperReport = JasperCompileManager.compileReport(JasperRuta);
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("itemDataSource", cobrojrb);
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());        
            OutputStream outputStream = new FileOutputStream(new File(outPutFile));
            
         JasperExportManager.exportReportToPdfStream(jasperprint, outputStream);
         byte[] ReportBytes = convertDocToByteArray(outPutFile);
         return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporte", ReportBytes);
    }catch (Exception ex){
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los datos de la pelicula.", "getComp " + ex.getMessage());
    }
}
      
    /*   public Respuesta ganerateJasperMovieInfo(Movie m) throws JRException, FileNotFoundException, IOException {
          report r  = new report();
          r.setNombre(m.getMovieNombre());
          Integer i,j,k;
          for(Tanda t : m.getTandaList()){
      //        for(Comprobante c : t.ge)
          }
           
          Collections.sort(rM, (reportMovie p1, reportMovie p2) -> { 
                return p2.getCantidad().compareTo(p1.getCantidad());
            } 
            );
          
         String ruta = context.getRealPath("/");
         String JasperRuta = ruta+ "\\jasper\\movies.jrxml";
         String pdfRuta = ruta+ "\\jasper\\jasperPrueba.pdf";
         String outPutFile = pdfRuta;
       
         JRBeanCollectionDataSource cobrojrb = new JRBeanCollectionDataSource(rM);
            JasperReport jasperReport = JasperCompileManager.compileReport(JasperRuta);
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("itemDataSource", cobrojrb);
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());        
            OutputStream outputStream = new FileOutputStream(new File(outPutFile));
            
         JasperExportManager.exportReportToPdfStream(jasperprint, outputStream);
         byte[] ReportBytes = convertDocToByteArray(outPutFile);
         return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporte", ReportBytes);
    }catch (Exception ex){
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los datos de la pelicula.", "getComp " + ex.getMessage());
    }
}*/
      
       public static byte[] convertDocToByteArray(String path)throws FileNotFoundException, IOException{
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }

}
