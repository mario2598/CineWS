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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    public Respuesta ganerateJasperReport(Long id) throws JRException, FileNotFoundException, IOException {

        try {
            String ruta = context.getRealPath("/");
            String JasperRuta = ruta + "\\jasper\\Movie.jrxml";
            String pdfRuta = ruta + "\\jasper\\jasperPrueba.pdf";
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
            parametros.put("P_ID", id);
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, parametros, conn);
            OutputStream outputStream = new FileOutputStream(new File(outPutFile));

            JasperExportManager.exportReportToPdfStream(jasperprint, outputStream);
            byte[] ReportBytes = convertDocToByteArray(outPutFile);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporte", ReportBytes);
        } catch (Exception ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los datos de la pelicula.", "getComp " + ex.getMessage());
        }
    }

     public Respuesta ganerateJasperMovieList(Long id) throws JRException, FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        
          try {
            String ruta = context.getRealPath("/");
            String JasperRuta = ruta + "\\jasper\\Movie.jrxml";
            String pdfRuta = ruta + "\\jasper\\jasperPrueba.pdf";
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
            parametros.put("P_ID", id);
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
