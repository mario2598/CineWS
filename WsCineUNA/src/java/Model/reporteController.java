/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Service.ComprobanteService;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

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
    
    public Respuesta ganerateJasperReport(Long id) throws JRException, FileNotFoundException, IOException {

        try {
            String ruta = context.getRealPath("/");
            String JasperRuta = ruta + "\\jasper\\CineUNAReport.jrxml";
            String pdfRuta = ruta + "\\jasper\\MovieReport.pdf";
            String outPutFile = pdfRuta;          
            String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
            String dbDriver = "oracle.jdbc.driver.OracleDriver";
            String dbUname = "CineUNA";
            String dbPwd = "una";
            Class.forName(dbDriver);
             // Get the connection
             Connection conn = DriverManager.getConnection(dbUrl, dbUname, dbPwd);
            JasperReport jasperReport = JasperCompileManager.compileReport(JasperRuta);
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("P_ID", id.intValue());
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, parametros,conn);
            OutputStream outputStream = new FileOutputStream(new File(outPutFile));

            JasperExportManager.exportReportToPdfStream(jasperprint, outputStream);
            byte[] ReportBytes = convertDocToByteArray(outPutFile);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporte", ReportBytes);
        } catch (Exception ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los datos de la pelicula.", "getComp " + ex.getMessage());
        }
    }

         public Respuesta ganerateJasperComp(Long id) throws JRException, FileNotFoundException, IOException, ClassNotFoundException, SQLException {      
         try {
            String ruta = context.getRealPath("/");
            String JasperRuta = ruta + "\\jasper\\ComprobanteCompraCineUNA.jrxml";
            String pdfRuta = ruta + "\\jasper\\ComprobanteCINEUNA.pdf";
            String outPutFile = pdfRuta;          
            String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
            String dbDriver = "oracle.jdbc.driver.OracleDriver";
            String dbUname = "CineUNA";
            String dbPwd = "una";
            Class.forName(dbDriver);
             // Get the connection
             Connection conn = DriverManager.getConnection(dbUrl, dbUname, dbPwd);
            JasperReport jasperReport = JasperCompileManager.compileReport(JasperRuta);
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("P_ID",id.intValue());
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, parametros, conn);
            OutputStream outputStream = new FileOutputStream(new File(outPutFile));

            JasperExportManager.exportReportToPdfStream(jasperprint, outputStream);
            byte[] ReportBytes = convertDocToByteArray(outPutFile);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporte", ReportBytes);
        } catch (Exception ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al crear comprobante.", "getComp " + ex.getMessage());
        }
}
    
     public Respuesta ganerateJasperMovieList(String d1,String d2) throws JRException, FileNotFoundException, IOException, ClassNotFoundException, SQLException {      
       
           LocalDate f1 = LocalDate.parse( d1 );
           LocalDate f2 = LocalDate.parse( d2 );
 
         try {
            String ruta = context.getRealPath("/");
            String JasperRuta = ruta + "\\jasper\\MoviesListReport.jrxml";
            String pdfRuta = ruta + "\\jasper\\MovieListReport.pdf";
            String outPutFile = pdfRuta;          
            String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
            String dbDriver = "oracle.jdbc.driver.OracleDriver";
            String dbUname = "CineUNA";
            String dbPwd = "una";
            Class.forName(dbDriver);
             // Get the connection
             Connection conn = DriverManager.getConnection(dbUrl, dbUname, dbPwd);
            JasperReport jasperReport = JasperCompileManager.compileReport(JasperRuta);
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("P_DATE1", Date.from(f1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            parametros.put("P_DATE2", Date.from(f2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, parametros, conn);
            OutputStream outputStream = new FileOutputStream(new File(outPutFile));

            JasperExportManager.exportReportToPdfStream(jasperprint, outputStream);
            byte[] ReportBytes = convertDocToByteArray(outPutFile);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporte", ReportBytes);
        } catch (Exception ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los datos de la pelicula.", "getComp " + ex.getMessage());
        }
}
    public static byte[] convertDocToByteArray(String path) throws FileNotFoundException, IOException {
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
